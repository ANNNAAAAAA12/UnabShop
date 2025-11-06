package ana.ramos.unab.edu.unabshop

import com.google.firebase.firestore.PropertyName

/**
 * Representa un producto en la tienda.
 * El uso de 'data class' nos da funciones útiles como .copy(), .toString(), etc.
 */
data class Producto(
        // No guardamos el ID como un campo aquí, se manejará por separado
        // para evitar conflictos con la deserialización de Firebase.
        var nombre: String = "",
        var precio: Double = 0.0,
        var descripcion: String = "",
        @get:PropertyName("imagen_url") @set:PropertyName("imagen_url")
        var imagenUrl: String = "", // Usamos PropertyName para mapear el campo en Firebase
        var activo: Boolean = true
        )


