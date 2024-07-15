package alejandroarcas.authsample.presentation.model

sealed interface UserEvent {
    data class Error(val error: UiText): UserEvent
}