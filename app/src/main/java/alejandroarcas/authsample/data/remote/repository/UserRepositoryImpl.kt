package alejandroarcas.authsample.data.remote.repository

import alejandroarcas.authsample.data.remote.api.UserApi
import alejandroarcas.authsample.data.remote.model.TokenResponse
import alejandroarcas.authsample.data.remote.model.UseRegisterRequest
import alejandroarcas.authsample.data.remote.model.UserLoginRequest
import alejandroarcas.authsample.domain.model.DataError
import alejandroarcas.authsample.domain.model.Result
import alejandroarcas.authsample.domain.repository.UserRepository
import retrofit2.HttpException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi
) : UserRepository {

    override suspend fun login(
        userAuthRequest: UserLoginRequest
    ): Result<TokenResponse, DataError.Network> {
        return try {
            Result.Success(userApi.login(userAuthRequest))
        } catch (e: HttpException) {
            when (e.code()) {
                409 -> Result.Error(DataError.Network.CONFLICT)
                404 -> Result.Error(DataError.Network.NOT_FOUND)
                401 -> Result.Error(DataError.Network.UNAUTHORIZED)
                413 -> Result.Error(DataError.Network.TOO_MANY_REQUESTS)
                else -> Result.Error(DataError.Network.UNKNOWN)
            }
        }
    }

    override suspend fun register(
        userRegisterRequest: UseRegisterRequest
    ): Result<Boolean, DataError.Network> {
        return try {
            Result.Success(userApi.register(userRegisterRequest))
        } catch (e: HttpException) {
            when (e.code()) {
                409 -> Result.Error(DataError.Network.CONFLICT)
                404 -> Result.Error(DataError.Network.NOT_FOUND)
                401 -> Result.Error(DataError.Network.UNAUTHORIZED)
                413 -> Result.Error(DataError.Network.TOO_MANY_REQUESTS)
                else -> Result.Error(DataError.Network.UNKNOWN)
            }
        }
    }
}