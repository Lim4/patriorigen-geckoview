# Patriorigen-gecko

Aplicación Android que muestra [Patriorigen](https://patriorigen.es) mediante **GeckoView**, el componente de Mozilla basado en el motor Gecko de Firefox.

## Descargar e instalar

Descarga **[Patriorigen-gecko.apk desde la última versión (Releases)](https://github.com/Lim4/patriorigen-geckoview/releases/latest)**.

La aplicación requiere **Android 8.0 o posterior** y dispositivos **ARM64**. Para instalarla, abre el archivo APK descargado y confirma la instalación. Android puede pedirte que autorices la instalación desde esa fuente.

Las versiones publicadas aquí son compilaciones de depuración. Si Android no permite actualizar sobre una versión anterior, desinstala la anterior y vuelve a instalar esta; las compilaciones pueden estar firmadas con claves distintas.

## Funciones

- Acceso a la web de Patriorigen desde una aplicación Android.
- Escaneo de productos con la cámara, según las funciones disponibles en la web.
- GeckoView integrado, sin abrir un navegador externo.

## Permisos

La aplicación declara los siguientes permisos:

| Permiso | Para qué se utiliza |
| --- | --- |
| **Internet** (`INTERNET`) | Cargar Patriorigen y conectarse a los servicios utilizados por la web. |
| **Cámara** (`CAMERA`) | Permitir el escaneo de productos cuando la web solicita acceso a la cámara. |
| **Micrófono** (`RECORD_AUDIO`) | Atender solicitudes de audio de la web. Android solo concede el acceso tras mostrar la solicitud de permiso. |

La política de privacidad de Patriorigen indica que el vídeo de la cámara se procesa en el navegador y no se almacena ni se envía a sus servidores. Consulta la [política de privacidad](https://patriorigen.es/privacidad.html) para más información.

## Créditos

- **Patriorigen:** la web, la marca y sus contenidos corresponden a **Resistencia Digital SL**, según su [aviso legal](https://patriorigen.es/aviso-legal.html).
- **GeckoView:** proyecto de **Mozilla**, basado en la tecnología Gecko que utiliza Firefox. Consulta la [documentación oficial](https://firefox-source-docs.mozilla.org/mobile/android/geckoview/) y el [proyecto GeckoView](https://github.com/mozilla/geckoview).
- **Datos de productos:** la web de Patriorigen consulta [Open Food Facts](https://world.openfoodfacts.org/).

Este proyecto es un cliente Android independiente y no está afiliado ni respaldado oficialmente por Mozilla ni por Resistencia Digital SL. Los nombres, marcas y contenidos citados pertenecen a sus respectivos titulares.

## Compilar

El repositorio incluye un flujo de GitHub Actions para generar la APK. Para obtener una compilación desde el código:

1. Abre la pestaña **Actions** del repositorio.
2. Selecciona **Build APK** y pulsa **Run workflow**.
3. Al terminar, descarga el artefacto de la ejecución.

La APK publicada en **Releases** es la descarga recomendada para instalar la aplicación.

## Licencias

GeckoView se distribuye bajo la licencia **Mozilla Public License 2.0 (MPL 2.0)**. La web, la marca y el contenido de Patriorigen mantienen los derechos de sus titulares. Este repositorio no incluye un archivo de licencia propio para el código de la aplicación.
