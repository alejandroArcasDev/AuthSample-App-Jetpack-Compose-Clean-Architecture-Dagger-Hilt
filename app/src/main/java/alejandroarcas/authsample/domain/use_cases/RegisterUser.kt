package alejandroarcas.authsample.domain.use_cases

import alejandroarcas.authsample.data.remote.model.UseRegisterRequest
import alejandroarcas.authsample.data.remote.model.UserLoginRequest
import alejandroarcas.authsample.domain.model.DataError
import alejandroarcas.authsample.domain.model.Result
import alejandroarcas.authsample.domain.repository.UserRepository
import javax.inject.Inject

class RegisterUser @Inject constructor(
    private val authRepository: UserRepository
) {
    suspend operator fun invoke(userRegisterRequest: UseRegisterRequest): Result<Boolean, DataError.Network> {
        return authRepository.register(userRegisterRequest)
    }
}