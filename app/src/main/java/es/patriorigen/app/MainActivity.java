package es.patriorigen.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import org.mozilla.geckoview.GeckoRuntime;
import org.mozilla.geckoview.GeckoSession;
import org.mozilla.geckoview.GeckoView;

public final class MainActivity extends Activity {
    private GeckoRuntime runtime;
    private GeckoSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        GeckoView view = new GeckoView(this);
        setContentView(view);
        runtime = GeckoRuntime.create(this);
        session = new GeckoSession();
        session.open(runtime);
        view.setSession(session);
        session.loadUri("https://patriorigen.es");
    }

    @Override
    protected void onDestroy() {
        if (session != null) session.close();
        super.onDestroy();
    }
}
