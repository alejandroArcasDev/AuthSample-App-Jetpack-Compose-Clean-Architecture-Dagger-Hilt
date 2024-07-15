package alejandroarcas.authsample.presentation.screens.login_screen

import alejandroarcas.authsample.data.local.DataStoreRepository
import alejandroarcas.authsample.data.remote.model.UserLoginRequest
import alejandroarcas.authsample.domain.model.Result
import alejandroarcas.authsample.domain.model.UserDataValidator
import alejandroarcas.authsample.domain.use_cases.LoginUser
import alejandroarcas.authsample.presentation.model.UserEvent
import alejandroarcas.authsample.presentation.model.asUiText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUser,
    private val dataStoreRepository: DataStoreRepository,
    private val userDataValidator: UserDataValidator
) : ViewModel() {

    private val _navigateToProfileScreen = MutableStateFlow(false)
    val navigateToProfileScreen = _navigateToProfileScreen.asStateFlow()

    private val _channel = Channel<UserEvent>()
    val events = _channel.receiveAsFlow()

    private val _userNameText = MutableStateFlow("")
    val userNameText = _userNameText.asStateFlow()

    private val _passwordText = MutableStateFlow("")
    val passwordText = _passwordText.asStateFlow()

    private val _checkedState = MutableStateFlow(true)
    val checkedState = _checkedState.asStateFlow()


    /**
     * Function to handle username change
     * @param userName String
     * @author alejandroarcas
     */
    fun onUserNameChange(userName: String) {
        _userNameText.value = userName
    }

    /**
     * Function to handle password change
     * @param password String
     * @author alejandroarcas
     */
    fun onPasswordChange(password: String) {
        _passwordText.value = password
    }

    /**
     * Function to handle checkbox change
     * @param checked Boolean
     * @author alejandroarcas
     */
    fun onCheckedChange(checked: Boolean) {
        _checkedState.value = checked
    }

    /**
     * Function to handle login button click
     * @author alejandroarcas
     */
    fun onLoginClick() {
        when (val result = userDataValidator.validatePassword(_passwordText.value)) {
            is Result.Error -> {
                viewModelScope.launch {
                    val errorMessage = result.error.asUiText()
                    _channel.send(UserEvent.Error(errorMessage))
                }
            }

            is Result.Success -> {
                viewModelScope.launch {
                    when (val result2 =
                        loginUseCase(UserLoginRequest(_userNameText.value, _passwordText.value))) {
                        is Result.Success -> {
                            if (_checkedState.value) {
                                dataStoreRepository.saveToken(result2.data.token)
                            }
                            _navigateToProfileScreen.value = true
                        }

                        is Result.Error -> {
                            val errorMessage = result2.error.asUiText()
                            _channel.send(UserEvent.Error(errorMessage))
                        }
                    }
                }
            }
        }


    }

}

