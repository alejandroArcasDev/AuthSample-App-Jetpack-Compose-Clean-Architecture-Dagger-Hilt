package alejandroarcas.authsample.data.remote.api

import alejandroarcas.authsample.data.remote.model.TokenResponse
import alejandroarcas.authsample.data.remote.model.UseRegisterRequest
import alejandroarcas.authsample.data.remote.model.UserLoginRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("auth/login")
    suspend fun login(@Body user: UserLoginRequest): TokenResponse

    @POST("auth/register")
    suspend fun register(@Body user: UseRegisterRequest): Boolean
}