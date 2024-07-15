package alejandroarcas.authsample.navigation

sealed class Screens(val route: String) {
    object LoginScreen: Screens("LoginScreen")
    object RegisterScreen: Screens("RegisterScreen")
    object ProfileScreen: Screens("ProfileScreen")
}