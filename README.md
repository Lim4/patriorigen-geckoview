# Patriorigen-gecko

Aplicación Android que abre https://patriorigen.es con GeckoView de Mozilla. Incluye el icono original de Patriorigen y permisos de cámara/micrófono.

## Descargar la APK

Cada compilación de GitHub Actions publica el artefacto `Patriorigen-gecko-arm64-apk`. En el repositorio, abre **Actions**, selecciona la ejecución completada más reciente y descarga el artefacto.

También puedes iniciar una compilación manual desde **Actions → Build APK → Run workflow**.

La APK está optimizada para Android ARM64 (la arquitectura de la mayoría de móviles actuales) y requiere Android 8.0 o posterior. Es una APK de depuración. Para instalar esta versión encima de la anterior, desinstala primero la anterior, ya que las compilaciones de GitHub Actions usan firmas de depuración distintas.
