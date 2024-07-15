package alejandroarcas.authsample.presentation.screens.register_screen

import alejandroarcas.authsample.data.remote.model.UseRegisterRequest
import alejandroarcas.authsample.domain.model.DataError
import alejandroarcas.authsample.domain.model.Result
import alejandroarcas.authsample.domain.model.UserDataValidator
import alejandroarcas.authsample.domain.use_cases.RegisterUser
import alejandroarcas.authsample.presentation.model.UserEvent
import alejandroarcas.authsample.presentation.model.asUiText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUser,
    private val userDataValidator: UserDataValidator
) : ViewModel() {

    private val _channel = Channel<UserEvent>()
    val events = _channel.receiveAsFlow()

    private val _emailText = MutableStateFlow("")
    val emailText = _emailText.asStateFlow()

    private val _usernameText = MutableStateFlow("")
    val usernameText = _usernameText.asStateFlow()

    private val _passwordText = MutableStateFlow("")
    val passwordText = _passwordText.asStateFlow()

    private val _navigateToLoginScreen = MutableStateFlow(false)
    val navigateToLoginScreen = _navigateToLoginScreen.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun onChangeEmail(email: String) {
        _emailText.value = email
    }

    fun onChangeUsername(username: String) {
        _usernameText.value = username
    }

    fun onChangePassword(password: String) {
        _passwordText.value = password
    }


    private fun navigateToLoginScreen(value: Boolean) {
        _navigateToLoginScreen.value = value
    }

    fun onRegisterClick() {
        when (val result = userDataValidator.validatePassword(_passwordText.value)) {
            is Result.Error -> {
                viewModelScope.launch {
                    val errorMessage = result.error.asUiText()
                    _channel.send(UserEvent.Error(errorMessage))
                }
            }

            is Result.Success -> {
                when (val result2 = userDataValidator.validateEmail(_emailText.value)) {
                    is Result.Error -> {
                        viewModelScope.launch {
                            val errorMessage = result2.error.asUiText()
                            _channel.send(UserEvent.Error(errorMessage))
                        }
                    }
                    is Result.Success -> {
                        viewModelScope.launch {
                            when (val result3 = registerUseCase(
                                UseRegisterRequest(
                                    _emailText.value,
                                    _usernameText.value,
                                    _passwordText.value
                                )
                            )) {
                                is Result.Success -> {
                                    if (result3.data) {
                                        // User exists
                                        val errorMessage = DataError.UserError.EXISTS.asUiText()
                                        _channel.send(UserEvent.Error(errorMessage))
                                        navigateToLoginScreen(false)
                                    } else {
                                        _isLoading.value = true
                                        delay(2500)
                                        navigateToLoginScreen(true)
                                    }
                                }

                                is Result.Error -> {
                                    val errorMessage = result3.error.asUiText()
                                    _channel.send(UserEvent.Error(errorMessage))
                                }
                            }
                        }
                    }
                }
            }
        }
    }



}