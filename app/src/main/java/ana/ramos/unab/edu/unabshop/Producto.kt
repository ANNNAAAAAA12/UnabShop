package ana.ramos.unab.edu.unabshop

import com.google.firebase.firestore.PropertyName

/**
 * Representa un producto en la tienda.
 * El uso de 'data class' nos da funciones útiles como .copy(), .toString(), etc.
 */
data class Producto(

        var nombre: String = "",
        var precio: Double = 0.0,
        var descripcion: String = "",
        @get:PropertyName("imagen_url") @set:PropertyName("imagen_url")
        var imagenUrl: String = "",
        var activo: Boolean = true
        )


