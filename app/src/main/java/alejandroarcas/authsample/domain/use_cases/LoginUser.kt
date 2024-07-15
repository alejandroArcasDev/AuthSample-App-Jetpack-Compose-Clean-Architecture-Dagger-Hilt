package alejandroarcas.authsample.domain.use_cases

import alejandroarcas.authsample.data.remote.model.TokenResponse
import alejandroarcas.authsample.data.remote.model.UserLoginRequest
import alejandroarcas.authsample.domain.model.DataError
import alejandroarcas.authsample.domain.model.Result
import alejandroarcas.authsample.domain.repository.UserRepository
import javax.inject.Inject

class LoginUser @Inject constructor(
    private val authRepository: UserRepository
) {
    suspend operator fun invoke(userAuthRequest: UserLoginRequest): Result<TokenResponse, DataError.Network> {
        return authRepository.login(userAuthRequest)
    }
}