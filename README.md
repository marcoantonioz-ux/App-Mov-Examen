# Perfil de Marco con Jetpack Compose y Google Maps

Aplicación Android para la asignatura Desarrollo de aplicaciones móviles de la Universidad Politécnica de Pachuca.

La pantalla presenta una portada de la UPP, fotografía circular, datos de Marco Antonio Zarco Espinoza, descripción profesional, diez tecnologías deslizables sin barra horizontal, Google Maps con marcador y un botón flotante que muestra un Snackbar.

## Abrir el proyecto

1. Abre esta carpeta en Android Studio y espera a que termine la sincronización.
2. Configura tu clave de Google Maps en local.properties.
3. Ejecuta el módulo app en un dispositivo o emulador con servicios de Google y conexión a Internet.

El proyecto utiliza SDK 33, JDK 11, Gradle 7.5, Android Gradle Plugin 7.3.1 y Kotlin 1.7.20. Incluye Gradle Wrapper.

Para compilar en Windows:

```powershell
.\gradlew.bat assembleDebug
```

## Google Maps

Habilita Maps SDK for Android en Google Cloud y autoriza el paquete mx.edu.upp.examen junto con la huella SHA-1 de la firma que utilices. Conserva la clave en local.properties:

```properties
MAPS_API_KEY=tu_clave
```

La clave, las firmas y los archivos locales de configuración están excluidos del repositorio. Puedes obtener la huella del certificado de depuración mediante la tarea signingReport. El archivo local.properties.example sirve como guía de configuración.

## Personalización

- DatosPerfil.kt contiene nombre, correo, subtítulo, descripción, tecnologías, coordenadas y zoom.
- foto_perfil.jpg contiene la fotografía del perfil.
- portada_upp.jpg contiene la imagen de portada.
- MainActivity.kt contiene colores, dimensiones, formas y el mensaje del botón.

La ubicación es fija y editable; la aplicación no consulta el GPS. Las coordenadas configuradas son 19.98287052157675, -98.68483830955036.

## Documentación

La carpeta docs reúne el reporte en Word y PDF y las capturas de ejecución. El reporte incluye el procedimiento, los bloques de código y las evidencias del mapa y del Snackbar.

## Créditos

Fotografía de portada: Universidad Politécnica de Pachuca, https://www.upp.edu.mx/mecasup/images/slider/slider0.jpg

Documentación de Maps Compose: https://developers.google.com/maps/documentation/android-sdk/maps-compose
