package alejandroarcas.authsample.data.remote.model

data class UseRegisterRequest(
    val email: String,
    val username: String,
    val password: String
)
