package mx.edu.upp.examen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import kotlinx.coroutines.launch

// COLORES: personaliza aquí el tema de la pantalla.
private val Morado = Color(0xFF6418AC)
private val Fondo = Color(0xFFFAF8FF)
private val Lavanda = Color(0xFFF0E7FB)
private val Tinta = Color(0xFF171226)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = android.graphics.Color.rgb(58, 25, 88)
        window.navigationBarColor = android.graphics.Color.rgb(250, 248, 255)
        setContent { MaterialTheme { Perfil() } }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Perfil(mostrarMapa: Boolean = BuildConfig.MAPS_CONFIGURED) {
    val snackbar = remember { SnackbarHostState() }
    val tareas = rememberCoroutineScope()
    Scaffold(containerColor = Fondo, snackbarHost = { SnackbarHost(snackbar) },
        floatingActionButton = {
            FloatingActionButton(onClick = { tareas.launch {
                snackbar.showSnackbar("¡Hola! Soy Marco. Gracias por visitar mi perfil.",
                    duration = SnackbarDuration.Long)
            } }, shape = CircleShape, containerColor = Morado, contentColor = Color.White,
                modifier = Modifier.size(60.dp)) { Text("+", fontSize = 38.sp) }
        }) { margen ->
        // Scroll vertical para consultar toda la pantalla en teléfonos pequeños.
        Column(Modifier.fillMaxSize().padding(margen).verticalScroll(rememberScrollState())) {
            Portada()
            Column(Modifier.offset(y = (-45).dp).padding(horizontal = 24.dp)) {
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    // FOTO: reemplaza res/drawable/foto_perfil.jpg.
                    Image(painterResource(R.drawable.foto_perfil), "Fotografía de Marco Antonio",
                        Modifier.size(104.dp).shadow(7.dp, CircleShape).clip(CircleShape)
                            .border(4.dp, Color.White, CircleShape), contentScale = ContentScale.Crop)
                    Surface(color = Lavanda, shape = CircleShape,
                        modifier = Modifier.padding(bottom = 8.dp)) {
                        Text("UPP · TIID", Modifier.padding(horizontal = 15.dp, vertical = 8.dp),
                            fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Morado)
                    }
                }
                Spacer(Modifier.height(18.dp))
                Text(DatosPerfil.nombre, color = Tinta, fontSize = 25.sp,
                    lineHeight = 30.sp, fontWeight = FontWeight.ExtraBold)
                Spacer(Modifier.height(6.dp))
                Text(DatosPerfil.subtitulo, color = Morado, fontSize = 14.sp,
                    lineHeight = 20.sp, fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(5.dp))
                Text(DatosPerfil.correo, color = Color(0xFF756A83), fontSize = 11.sp)
                Spacer(Modifier.height(14.dp))
                Text(DatosPerfil.descripcion, color = Color(0xFF514A5B), fontSize = 14.sp,
                    lineHeight = 21.sp)
                Spacer(Modifier.height(25.dp))
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Text("</>", fontWeight = FontWeight.ExtraBold, color = Morado, fontSize = 19.sp)
                    Spacer(Modifier.width(8.dp))
                    Text("LENGUAJES Y TECNOLOGÍAS", Modifier.weight(1f), color = Tinta,
                        fontSize = 12.sp, fontWeight = FontWeight.ExtraBold)
                    Text("Desliza →", color = Color(0xFF9689AA), fontSize = 10.sp)
                }
                Spacer(Modifier.height(14.dp))
                // LazyRow deja deslizar las diez etiquetas SIN BARRA horizontal.
                LazyRow(horizontalArrangement = Arrangement.spacedBy(9.dp)) {
                    items(DatosPerfil.habilidades) { Etiqueta(it) }
                }
                Spacer(Modifier.height(23.dp))
                // MAPA: cambia aquí altura y esquinas; coordenadas en DatosPerfil.kt.
                Box(Modifier.fillMaxWidth().height(235.dp).clip(RoundedCornerShape(23.dp))
                    .border(1.dp, Color(0xFFEAE2F2), RoundedCornerShape(23.dp))) {
                    if (mostrarMapa) MapaPerfil() else {
                        Column(Modifier.fillMaxSize().background(Lavanda).padding(24.dp),
                            verticalArrangement = Arrangement.Center) {
                            Text("Mi ubicación", color = Morado, fontWeight = FontWeight.Bold)
                            Spacer(Modifier.height(8.dp))
                            Text("Configura MAPS_API_KEY en local.properties y recompila.",
                                color = Color(0xFF665B73), fontSize = 13.sp)
                        }
                    }
                }
                Spacer(Modifier.height(9.dp))
                Text("MI UBICACIÓN", color = Color(0xFF9689AA), fontSize = 9.sp,
                    letterSpacing = 1.5.sp)
                Spacer(Modifier.height(75.dp))
            }
        }
    }
}

@Composable
private fun Portada() {
    // PORTADA: sustituye portada_upp.jpg por tu propia imagen.
    Box(Modifier.fillMaxWidth().height(190.dp)) {
        Image(painterResource(R.drawable.portada_upp), "Instalaciones de la UPP",
            Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
        Box(Modifier.fillMaxSize().background(Brush.verticalGradient(
            listOf(Color(0x660E0920), Color.Transparent, Color(0x55401766)))))
        Surface(color = Color(0xBC21172D), shape = CircleShape,
            modifier = Modifier.padding(18.dp).align(Alignment.TopStart)) {
            Row(Modifier.padding(7.dp), verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(31.dp).background(Color.White, CircleShape),
                    contentAlignment = Alignment.Center) {
                    Text("UPP", fontSize = 11.sp, color = Morado, fontWeight = FontWeight.ExtraBold)
                }
                Spacer(Modifier.width(8.dp))
                Column(Modifier.padding(end = 10.dp)) {
                    Text("Universidad Politécnica", color = Color.White, fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold)
                    Text("de Pachuca", color = Color(0xFFE2D0EF), fontSize = 10.sp)
                }
            }
        }
    }
}

@Composable
private fun Etiqueta(nombre: String) {
    // Emblemas dibujados con iniciales. Puedes cambiar colores y siglas aquí.
    val color = when (nombre) {
        "C++" -> Color(0xFF1978BC); "C#" -> Color(0xFF7A24B2)
        "Java" -> Color(0xFFE05A33); "Kotlin" -> Color(0xFFAB43D3)
        "JavaScript" -> Color(0xFFB58A00); "PHP" -> Color(0xFF637BB3)
        "HTML" -> Color(0xFFDA572D); "CSS" -> Color(0xFF2678C9)
        "MySQL" -> Color(0xFF177B8D); else -> Color(0xFF159EBC)
    }
    val sigla = when (nombre) {
        "JavaScript" -> "JS"; "Kotlin" -> "K"; "Java" -> "J"; "MySQL" -> "SQL"
        else -> nombre.take(3)
    }
    Surface(color = Lavanda, shape = CircleShape) {
        Row(Modifier.padding(horizontal = 13.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(25.dp).background(color, RoundedCornerShape(7.dp)),
                contentAlignment = Alignment.Center) {
                Text(sigla, color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.ExtraBold)
            }
            Spacer(Modifier.width(8.dp))
            Text(nombre, color = Tinta, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
private fun MapaPerfil() {
    val punto = LatLng(DatosPerfil.latitud, DatosPerfil.longitud)
    val camara = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(punto, DatosPerfil.zoom)
    }
    GoogleMap(Modifier.fillMaxSize(), cameraPositionState = camara,
        uiSettings = MapUiSettings(scrollGesturesEnabled = false, zoomControlsEnabled = true)) {
        Marker(state = rememberMarkerState(position = punto), title = DatosPerfil.nombre,
            snippet = "Mi ubicación")
    }
}

// Preview sin consumo del mapa. Verifica el mapa real ejecutando la aplicación.
@Preview(showBackground = true, widthDp = 411, heightDp = 1100)
@Composable
private fun VistaPreviaPerfil() { MaterialTheme { Perfil(mostrarMapa = false) } }
