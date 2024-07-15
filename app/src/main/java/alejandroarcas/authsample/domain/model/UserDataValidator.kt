package alejandroarcas.authsample.domain.model

import javax.inject.Inject

class UserDataValidator @Inject constructor() {

    fun validatePassword(password: String): Result<Unit, DataError.PasswordError> {
        if (password.length < 8) {
            return Result.Error(DataError.PasswordError.TOO_SHORT)
        }

        val hasUppercaseChar = password.any { it.isUpperCase() }
        if (!hasUppercaseChar) {
            return Result.Error(DataError.PasswordError.NO_UPPERCASE)
        }

        val hasDigit = password.any { it.isDigit() }
        if (!hasDigit) {
            return Result.Error(DataError.PasswordError.NO_DIGIT)
        }

        return Result.Success(Unit)
    }

    fun validateEmail(email: String): Result<Unit, DataError.EmailError> {
        if (email.isEmpty()) {
            return Result.Error(DataError.EmailError.EMPTY)
        }

        val isValid = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        if (!isValid) {
            return Result.Error(DataError.EmailError.INVALID)
        }

        return Result.Success(Unit)
    }
}
