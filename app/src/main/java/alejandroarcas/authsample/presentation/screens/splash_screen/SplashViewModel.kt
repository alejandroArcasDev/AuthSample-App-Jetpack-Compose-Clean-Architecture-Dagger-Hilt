package alejandroarcas.authsample.presentation.screens.splash_screen

import alejandroarcas.authsample.data.local.DataStoreRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
): ViewModel() {

    init {
        readToken()
    }

    private var _token = MutableStateFlow("")
    var token = _token.asStateFlow()

    /**
     * Read token from DataStore to check if user is logged in
     * @author alejandroarcas
     */
    private fun readToken() {
        viewModelScope.launch {
            dataStoreRepository.readToken().collect {
                if (it != null ) _token.value = it
            }
        }
    }
}