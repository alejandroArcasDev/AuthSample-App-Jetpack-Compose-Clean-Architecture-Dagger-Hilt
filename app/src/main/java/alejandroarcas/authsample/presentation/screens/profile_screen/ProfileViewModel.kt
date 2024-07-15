package alejandroarcas.authsample.presentation.screens.profile_screen

import alejandroarcas.authsample.data.local.DataStoreRepository
import alejandroarcas.authsample.domain.repository.UserRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    private val _userWantsToLogout = MutableStateFlow(false)
    val userWantsToLogout = _userWantsToLogout.asStateFlow()

    private val _isLoggedOut = MutableStateFlow(false)
    val isLoggedOut = _isLoggedOut.asStateFlow()

    fun userWantsToLogout(value: Boolean) {
        _userWantsToLogout.value = value
    }

    fun onLogOutChange(value: Boolean) {
        _isLoggedOut.value = value
    }

    /**
    * Function to clear the token
     * @author alejandroarcas
    */
    suspend fun updateDatastore(){
        viewModelScope.launch {
            dataStoreRepository.clearToken()
            onLogOutChange(true)
        }
    }

    /**
     * Function to edit profile and navigate to edit profile screen
     * @author alejandroarcas
     */
    fun onClickEditProfile(){

    }

    /**
     * Function to delete account and navigate to login screen
     * @author alejandroarcas
     */
    fun onClickDeleteAcc(){

    }

    /**
     * Function to change password and navigate to change password screen
     * @author alejandroarcas
     */
    fun onClickChangePassword(){

    }
}