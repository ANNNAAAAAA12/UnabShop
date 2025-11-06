package ana.ramos.unab.edu.unabshop

// Imports de Compose para la UI y el Layout
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

// Imports de Material 3 (la versión moderna)
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults

// Imports del Runtime de Compose (para estados y efectos)
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

// Imports para la UI
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Import CLAVE para el ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


// --- ESTA ES TU PANTALLA COMPOSE "HomeScreen" ---
@OptIn(ExperimentalMaterial3Api::class)
// ESTA ES LA LÍNEA CORREGIDA
@Composable
fun HomeScreen(
    productoViewModel: ProductoViewModel = viewModel(),
    onClickLogout: () -> Unit )
 {
    // 1. Obtenemos los datos desde el ViewModel.
    // Compose observará estos estados y redibujará la pantalla cuando cambien.
    val listaProductosConId = productoViewModel.listaProductos.value
    val estaCargando = productoViewModel.cargando.value

    // LaunchedEffect se usa para llamar a funciones que no deben ejecutarse en cada recomposición,
    // como la carga inicial de datos desde Firebase.
    LaunchedEffect(Unit) {
        productoViewModel.cargarProductos()
    }

     // ... dentro de tu Composable HomeScreen
     Scaffold(
         topBar = {
             TopAppBar(
                 title = { Text("UNAB Shop - Productos") },
                 colors = TopAppBarDefaults.topAppBarColors(
                     containerColor = Color(0xFFFF9900),
                     titleContentColor = Color.White,
                     actionIconContentColor = Color.White
                 ),
                 actions = {
                     // Añadimos un botón de ícono para el logout
                     IconButton(onClick = onClickLogout) { // Usamos la función aquí
                         Icon(
                             // 1. CAMBIO: Usamos el ícono correcto de Logout
                             imageVector = Icons.Default.ArrowBack,
                             contentDescription = "Cerrar sesión"
                         )
                     }
                 }
             )
         }
     ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            if (estaCargando) {
                // Si está cargando, muestra una rueda de progreso.
                CircularProgressIndicator()
            } else {
                // Si ya cargó, muestra la lista de productos.
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // 3. Iteramos sobre la lista de productos obtenida del ViewModel.
                    // Desestructuramos el Par para tener 'id' y 'producto' por separado.
                    items(listaProductosConId) { (id, producto) ->
                        ProductoItem(
                            id = id,
                            producto = producto,
                            onEstadoChange = { nuevoEstado ->
                                // Llamamos a la función del ViewModel para cambiar el estado.
                                productoViewModel.cambiarEstadoProducto(id, nuevoEstado)
                            },
                            onEditClick = {
                                // Aquí pondrías la lógica para ir a la pantalla de edición.
                                // Por ejemplo: navController.navigate("editarProducto/$id")
                                println("Editar producto con ID: $id")
                            }
                        )
                    }
                }
            }
        }
    }
}


// --- Componente para mostrar CADA producto en la lista ---
@Composable
fun ProductoItem(
    id: String,
    producto: Producto,
    onEstadoChange: (Boolean) -> Unit,
    onEditClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = producto.nombre,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "$ ${"%.2f".format(producto.precio)}", // Formatea el precio a 2 decimales
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = producto.descripcion,
                fontSize = 14.sp,
                color = Color.Gray
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Columna para el Switch de 'Activo'
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(if (producto.activo) "Activo" else "Inactivo")
                    Spacer(modifier = Modifier.width(8.dp))
                    Switch(
                        checked = producto.activo,
                        onCheckedChange = onEstadoChange
                    )
                }
                // Botón para editar
                Button(onClick = onEditClick) {
                    Text("Editar")
                }
            }
        }
    }
}
