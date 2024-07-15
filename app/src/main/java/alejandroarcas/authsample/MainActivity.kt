package alejandroarcas.authsample

import alejandroarcas.authsample.navigation.NavGraph
import alejandroarcas.authsample.navigation.Screens
import alejandroarcas.authsample.presentation.screens.login_screen.LoginViewModel
import alejandroarcas.authsample.presentation.ui.AuthSampleTheme
import android.os.Build
import android.os.Bundle
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AuthSampleTheme {
                val userLogged = intent.getBooleanExtra("isLogged", false)

                val loginViewModel: LoginViewModel = hiltViewModel()

                val navController = rememberNavController()

                Scaffold { paddingValues ->

                    NavGraph(
                        startDestination = if (userLogged) Screens.ProfileScreen.route else Screens.LoginScreen.route,
                        startDestinationViewModel = loginViewModel,
                        navHostController = navController,
                        modifier = Modifier.padding(paddingValues)
                    )

                }
            }
        }
    }
}

