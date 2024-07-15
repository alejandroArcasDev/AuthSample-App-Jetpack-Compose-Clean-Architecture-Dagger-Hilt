package alejandroarcas.authsample.presentation.model

import alejandroarcas.authsample.R
import alejandroarcas.authsample.domain.model.DataError
import alejandroarcas.authsample.domain.model.Result
import android.provider.ContactsContract.Data

fun DataError.asUiText(): UiText {

    return when (this) {
        DataError.Network.UNAUTHORIZED -> UiText.StringResource(
            R.string.error_unauthorized
        )

        DataError.Network.SERVER_ERROR -> UiText.StringResource(
            R.string.error_server
        )

        DataError.Network.REQUEST_TIMEOUT -> UiText.StringResource(
            R.string.error_timeout
        )

        DataError.Network.NO_INTERNET -> UiText.StringResource(
            R.string.error_no_internet
        )

        DataError.Network.TOO_MANY_REQUESTS -> UiText.StringResource(
            R.string.error_too_many_requests
        )

        DataError.Network.PAYLOAD_TOO_LARGE -> UiText.StringResource(
            R.string.error_payload_too_large
        )

        DataError.Network.CONFLICT -> UiText.StringResource(
            R.string.error_conflict
        )

        DataError.Network.NOT_FOUND -> UiText.StringResource(
            R.string.error_not_found
        )

        DataError.PasswordError.NO_DIGIT -> UiText.StringResource(
            R.string.error_no_digit
        )

        DataError.PasswordError.NO_UPPERCASE -> UiText.StringResource(
            R.string.error_no_uppercase
        )

        DataError.PasswordError.TOO_SHORT -> UiText.StringResource(
            R.string.error_too_short
        )

        DataError.EmailError.EMPTY -> UiText.StringResource(
            R.string.error_email_empty
        )

        DataError.EmailError.INVALID -> UiText.StringResource(
            R.string.error_email_invalid
        )

        DataError.UserError.EXISTS -> UiText.StringResource(
            R.string.error_user_exist
        )

        else -> UiText.StringResource(
            R.string.error_unknown
        )
    }
}

fun Result.Error<*, DataError>.asUiText(): UiText {
    return error.asUiText()
}