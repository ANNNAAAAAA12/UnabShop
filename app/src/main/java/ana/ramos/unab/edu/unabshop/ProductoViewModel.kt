package ana.ramos.unab.edu.unabshop

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ProductoViewModel : ViewModel() {

    private val repository = ProductoRepository()


    val listaProductos = mutableStateOf<List<Pair<String, Producto>>>(emptyList())
    val cargando = mutableStateOf(false)

    init {
        cargarProductos()
    }

    fun cargarProductos() {
        cargando.value = true
        repository.obtenerProductos { productos ->
            listaProductos.value = productos
            cargando.value = false
        }
    }

    fun cambiarEstadoProducto(idProducto: String, nuevoEstado: Boolean) {
        repository.actualizarEstadoProducto(idProducto, nuevoEstado) { success ->
            if (success) {
                Log.d("ViewModel", "Estado del producto $idProducto actualizado a $nuevoEstado")
                // Recargamos la lista para que la UI se actualice
                cargarProductos()
            } else {
                Log.e("ViewModel", "Error al actualizar el estado del producto $idProducto")
            }
        }
    }

    fun editarProducto(idProducto: String, producto: Producto) {
        repository.editarProducto(idProducto, producto) { success ->
            if (success) {
                Log.d("ViewModel", "Producto $idProducto editado con éxito.")
                cargarProductos()
            } else {
                Log.e("ViewModel", "Error al editar el producto $idProducto")
            }
        }
    }

    fun eliminarProducto(id: String) {
        repository.eliminarProducto(id) {
            cargarProductos()
        }
    }
}
