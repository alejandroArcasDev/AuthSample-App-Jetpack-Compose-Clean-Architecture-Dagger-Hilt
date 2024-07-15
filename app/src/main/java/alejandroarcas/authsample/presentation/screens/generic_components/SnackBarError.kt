package alejandroarcas.authsample.presentation.screens.generic_components

import alejandroarcas.authsample.presentation.model.UserEvent
import alejandroarcas.authsample.presentation.screens.login_screen.LoginViewModel
import android.widget.Toast
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
fun MySnackBar(
    loginViewModel: LoginViewModel,
    duration: SnackbarDuration = SnackbarDuration.Short,
) {
    val events = loginViewModel.events.collectAsState(initial = null)
    val context = LocalContext.current

    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(snackBarHostState) {
        events.value?.let { event ->
            when (event) {
                is UserEvent.Error -> {
                    println("Error: ${event.error.asString(context)}s")
                    snackBarHostState.showSnackbar(event.error.asString(context), duration = duration)
                }
            }
        }
    }

    SnackbarHost(
        hostState = snackBarHostState,
    )
}