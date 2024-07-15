package alejandroarcas.authsample.domain.repository

import alejandroarcas.authsample.data.remote.model.TokenResponse
import alejandroarcas.authsample.data.remote.model.UseRegisterRequest
import alejandroarcas.authsample.data.remote.model.UserLoginRequest
import alejandroarcas.authsample.domain.model.DataError
import alejandroarcas.authsample.domain.model.Result

interface UserRepository {

    suspend fun login(userAuthRequest: UserLoginRequest): Result<TokenResponse, DataError.Network>

    suspend fun register(userRegisterRequest: UseRegisterRequest): Result<Boolean, DataError.Network>
}