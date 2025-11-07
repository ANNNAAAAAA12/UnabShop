package ana.ramos.unab.edu.unabshop

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject

class ProductoRepository {

    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection("productos")

    /**
     * Obtiene una lista de todos los productos desde Firestore.
     * Asigna el ID del documento a una variable local después de crear el objeto.
     */
    fun obtenerProductos(onResult: (List<Pair<String, Producto>>) -> Unit) {
        collection.get()
            .addOnSuccessListener { result ->
                val productosConId = result.documents.mapNotNull { doc ->
                    val producto = doc.toObject<Producto>()
                    if (producto != null) {

                        Pair(doc.id, producto)
                    } else {
                        null
                    }
                }
                onResult(productosConId)
            }
            .addOnFailureListener {
                onResult(emptyList())
            }
    }

    /**
     * Actualiza el estado 'activo' de un producto en Firestore.
     * @param id El ID del documento del producto.
     * @param nuevoEstado true para activo, false para inactivo.
     */
    fun actualizarEstadoProducto(id: String, nuevoEstado: Boolean, onResult: (Boolean) -> Unit) {
        collection.document(id)
            .update("activo", nuevoEstado)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }

    /**
     * Actualiza todos los campos de un producto existente.
     * @param id El ID del documento del producto a editar.
     * @param producto El objeto Producto con los nuevos datos.
     */
    fun editarProducto(id: String, producto: Producto, onResult: (Boolean) -> Unit) {
        collection.document(id)
            .set(producto)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }

    fun eliminarProducto(id: String, onResult: (Boolean) -> Unit) {
        collection.document(id).delete()
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }

    fun agregarProducto(producto: Producto, onResult: (Boolean) -> Unit) {
        collection.add(producto)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }
}
