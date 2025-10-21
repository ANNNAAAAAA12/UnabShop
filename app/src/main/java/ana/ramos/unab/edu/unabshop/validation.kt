package ana.ramos.unab.edu.unabshop

import android.util.Patterns

fun validateEmail(email: String) : Pair<Boolean, String>{
    return when{
        email.isEmpty() -> Pair(false, "El correo electrónico no puede estar vacío")
        !email.endsWith("@test.com") -> Pair(false, "Este correo no es corporativo")
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

fun validateName(name: String) : Pair<Boolean, String> {
    return when{
        name.isEmpty() -> Pair(false, "La contraseña no puede estar vacía")
        name.length < 6 -> Pair(false, "La contraseña debe tener al menos 6 caracteres")
        else -> Pair(true, "")

    }
}
fun validateConfirmPassword(password: String, confirmPassword: String) : Pair<Boolean, String> {
    return when{
        password.isEmpty() -> Pair(false, "La contraseña no puede estar vacía")
        confirmPassword != password -> Pair(false, "Las contraseñas no coinciden")

        else -> Pair(true, "")

    }
}



