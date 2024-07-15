package alejandroarcas.authsample.domain.model

import android.provider.ContactsContract.Data

interface DataError: Error {
    enum class Network: DataError {
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        PAYLOAD_TOO_LARGE,
        SERVER_ERROR,
        UNAUTHORIZED,
        CONFLICT,
        NOT_FOUND,
        UNKNOWN
    }
    enum class Local: DataError {
        DISK_FULL
    }

    enum class PasswordError: DataError {
        TOO_SHORT,
        NO_UPPERCASE,
        NO_DIGIT
    }

    enum class EmailError: DataError {
        INVALID,
        EMPTY
    }

    enum class UserError: DataError {
        EXISTS
    }
}