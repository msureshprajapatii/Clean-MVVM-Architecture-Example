package com.suresh.latestandroidstructure.core.validation

object InputValidator {

    fun isEmailValid(email: String): Boolean {
        return email.isNotBlank() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPasswordValid(password: String): Boolean {
        return password.length >= 6
    }

    fun doPasswordsMatch(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }

    fun isFieldNotEmpty(vararg fields: String): Boolean {
        return fields.all { it.isNotBlank() }
    }
}