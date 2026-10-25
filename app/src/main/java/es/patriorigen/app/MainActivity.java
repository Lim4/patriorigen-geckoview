package es.patriorigen.app;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import org.mozilla.geckoview.GeckoRuntime;
import org.mozilla.geckoview.GeckoSession;
import org.mozilla.geckoview.GeckoView;

public final class MainActivity extends Activity {
    private static final int MEDIA_PERMISSION_REQUEST = 4101;

    private GeckoRuntime runtime;
    private GeckoSession session;
    private GeckoSession.PermissionDelegate.Callback pendingAndroidPermissionCallback;

    private final GeckoSession.PermissionDelegate permissionDelegate =
            new GeckoSession.PermissionDelegate() {
                @Override
                public void onAndroidPermissionsRequest(
                        GeckoSession session,
                        String[] permissions,
                        GeckoSession.PermissionDelegate.Callback callback) {
                    if (permissions == null || permissions.length == 0) {
                        callback.grant();
                        return;
                    }

                    boolean allGranted = true;
                    for (String permission : permissions) {
                        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                            allGranted = false;
                            break;
                        }
                    }

                    if (allGranted) {
                        callback.grant();
                    } else if (pendingAndroidPermissionCallback != null) {
                        callback.reject();
                    } else {
                        pendingAndroidPermissionCallback = callback;
                        requestPermissions(permissions, MEDIA_PERMISSION_REQUEST);
                    }
                }

                @Override
                public void onMediaPermissionRequest(
                        GeckoSession session,
                        String uri,
                        GeckoSession.PermissionDelegate.MediaSource[] video,
                        GeckoSession.PermissionDelegate.MediaSource[] audio,
                        GeckoSession.PermissionDelegate.MediaCallback callback) {
                    GeckoSession.PermissionDelegate.MediaSource camera =
                            findSource(video, GeckoSession.PermissionDelegate.MediaSource.SOURCE_CAMERA);
                    GeckoSession.PermissionDelegate.MediaSource microphone =
                            findSource(audio, GeckoSession.PermissionDelegate.MediaSource.SOURCE_MICROPHONE);

                    if ((video != null && camera == null) || (audio != null && microphone == null)) {
                        callback.reject();
                        return;
                    }
                    if ((camera != null
                                    && checkSelfPermission(Manifest.permission.CAMERA)
                                            != PackageManager.PERMISSION_GRANTED)
                            || (microphone != null
                                    && checkSelfPermission(Manifest.permission.RECORD_AUDIO)
                                            != PackageManager.PERMISSION_GRANTED)) {
                        callback.reject();
                        return;
                    }

                    String host = Uri.parse(uri).getHost();
                    if (host == null || host.isEmpty()) host = uri;

                    String requested = camera != null && microphone != null
                            ? "la cámara y el micrófono"
                            : camera != null ? "la cámara" : "el micrófono";

                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Permitir acceso")
                            .setMessage(host + " quiere usar " + requested + ".")
                            .setPositiveButton("Permitir",
                                    (dialog, which) -> callback.grant(camera, microphone))
                            .setNegativeButton("Rechazar",
                                    (dialog, which) -> callback.reject())
                            .setOnCancelListener(dialog -> callback.reject())
                            .show();
                }
            };

    private static GeckoSession.PermissionDelegate.MediaSource findSource(
            GeckoSession.PermissionDelegate.MediaSource[] sources, int sourceType) {
        if (sources == null) return null;
        for (GeckoSession.PermissionDelegate.MediaSource source : sources) {
            if (source.source == sourceType) return source;
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        GeckoView view = new GeckoView(this);
        setContentView(view);
        runtime = GeckoRuntime.create(this);
        session = new GeckoSession();
        session.setPermissionDelegate(permissionDelegate);
        session.open(runtime);
        view.setSession(session);
        session.loadUri("https://patriorigen.es");
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != MEDIA_PERMISSION_REQUEST) return;

        GeckoSession.PermissionDelegate.Callback callback = pendingAndroidPermissionCallback;
        pendingAndroidPermissionCallback = null;
        if (callback == null) return;

        boolean allGranted = grantResults.length > 0;
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                allGranted = false;
                break;
            }
        }

        if (allGranted) callback.grant();
        else callback.reject();
    }

    @Override
    protected void onDestroy() {
        if (session != null) session.close();
        super.onDestroy();
    }
}
