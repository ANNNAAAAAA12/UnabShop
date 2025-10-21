package ana.ramos.unab.edu.unabshop

import android.util.Patterns

fun validateEmail(email: String) : Pair<Boolean, String>{
    return when{
        email.isEmpty() -> Pair(false, "El correo electrónico no puede estar vacío")
        !email.endsWith("@test.com") -> Pair(false, "Este correo no es corporativo")
        Patterns.EMAIL_ADDRESS.matcher(email).matches() -> Pair(false, "El correo electrónico no es válido")
        else -> Pair(true, "")
    }


}


fun validatePassword(password: String) : Pair<Boolean, String> {
    return when{
        password.isEmpty() -> Pair(false, "La contraseña no puede estar vacía")
        password.length < 6 -> Pair(false, "La contraseña debe tener al menos 6 caracteres")
        !password.any{it.isDigit()} -> Pair(false, "La contraseña debe contener al menos un número")

        else -> Pair(true, "")

    }
}



