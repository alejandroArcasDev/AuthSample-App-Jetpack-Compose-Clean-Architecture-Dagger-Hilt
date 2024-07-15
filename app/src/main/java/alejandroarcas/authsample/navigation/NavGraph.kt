package alejandroarcas.authsample.navigation

import alejandroarcas.authsample.presentation.screens.profile_screen.ProfileScreen
import alejandroarcas.authsample.presentation.screens.login_screen.LoginScreen
import alejandroarcas.authsample.presentation.screens.login_screen.LoginViewModel
import alejandroarcas.authsample.presentation.screens.register_screen.RegisterScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(
    startDestination: String,
    startDestinationViewModel: LoginViewModel,
    navHostController: NavHostController,
    modifier: Modifier
) {
    NavHost(navController = navHostController, startDestination = startDestination, modifier = modifier) {

        composable(Screens.LoginScreen.route) {
            LoginScreen(startDestinationViewModel, navHostController)
        }

        composable(Screens.RegisterScreen.route) {
            RegisterScreen(hiltViewModel(), navHostController)
        }

        composable(Screens.ProfileScreen.route) {
            ProfileScreen(hiltViewModel(), navHostController)
        }

    }
}