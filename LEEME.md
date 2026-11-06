# Perfil de Marco en Jetpack Compose

Proyecto adaptado a tu Android Studio 2022.1: JDK 11 incluido con el IDE, SDK 33, Gradle 7.5, Android Gradle Plugin 7.3.1 y Kotlin 1.7.20. Incluye Gradle Wrapper. Abre esta carpeta en Android Studio, espera la sincronización y ejecuta el módulo app.

## Personalización

- DatosPerfil.kt contiene nombre, correo, descripción, 10 habilidades, coordenadas y zoom.
- Reemplaza app/src/main/res/drawable/foto_perfil.jpg para cambiar la foto.
- MainActivity.kt contiene la portada, colores, tamaños, tarjeta de mapa y mensaje del botón +.
- Las habilidades usan LazyRow y no muestran barra de desplazamiento.

## Activar Google Maps

1. En Google Cloud crea o selecciona un proyecto, habilita Maps SDK for Android y configura la facturación que solicite Google.
2. Crea una clave y restríngela a Maps SDK for Android y a una aplicación Android con paquete mx.edu.upp.examen y la huella SHA-1 de tu certificado de depuración. Obtén esa huella con la tarea Gradle signingReport.
3. En local.properties, en la raíz, conserva sdk.dir y añade una línea: MAPS_API_KEY=TU_CLAVE_REAL
4. Sincroniza y recompila. Usa un emulador con Google Play y conexión a internet.

En el proyecto local abierto en Android Studio ya se incorporó la clave de tu práctica anterior y se verificó la carga del mapa y el marcador. El ZIP de fuentes excluye local.properties. No se solicita GPS: la ubicación es fija y editable.

## Capturas para el reporte

En un emulador toma: perfil con foto y descripción; etiquetas deslizadas hasta Dart; mapa real con marcador; Snackbar después de pulsar +. Usa el botón de captura del emulador. No confundir la vista previa sin clave con una prueba del mapa.

## Estado de validación

Compilación assembleDebug completada correctamente el 5 de noviembre de 2026. La versión morada se instaló y ejecutó en Pixel XL API 36. Se verificaron la portada, foto, datos, desplazamiento de etiquetas sin barra, cartografía de Google Maps, marcador con ventana informativa y Snackbar del botón +. Las coordenadas definitivas son 19.98287052157675, -98.68483830955036. Las capturas reales están en Capturas_actualizadas y en el reporte PDF.

## Portada y diseño

Tema morado inspirado en el ejemplo proporcionado. Fotografía de portada obtenida del sitio oficial de la UPP: https://www.upp.edu.mx/mecasup/images/slider/slider0.jpg
Puedes sustituir portada_upp.jpg por tu propia imagen. Los emblemas de los lenguajes están dibujados con texto y colores en Compose.

Referencias: https://developers.google.com/maps/documentation/android-sdk/maps-compose y https://developers.google.com/maps/documentation/android-sdk/get-api-key
