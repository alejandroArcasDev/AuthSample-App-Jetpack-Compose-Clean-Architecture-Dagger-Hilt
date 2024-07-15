package alejandroarcas.authsample.presentation.screens.register_screen

import alejandroarcas.authsample.navigation.Screens
import alejandroarcas.authsample.presentation.model.UserEvent
import alejandroarcas.authsample.presentation.screens.generic_components.TextFieldLabel
import alejandroarcas.authsample.presentation.screens.generic_components.TopHeaderSphere
import alejandroarcas.authsample.presentation.screens.generic_components.TopHeaderText
import alejandroarcas.authsample.presentation.screens.login_screen.components.BottomSphere
import alejandroarcas.authsample.presentation.screens.login_screen.components.LoginButton
import alejandroarcas.authsample.presentation.screens.register_screen.components.RegisterButton
import alejandroarcas.authsample.presentation.screens.register_screen.components.RegisterTextField
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController

@Composable
fun RegisterScreen(registerViewModel: RegisterViewModel, navHostController: NavHostController) {

    val emailText by registerViewModel.emailText.collectAsStateWithLifecycle()
    val usernameText by registerViewModel.usernameText.collectAsStateWithLifecycle()
    val passwordText by registerViewModel.passwordText.collectAsStateWithLifecycle()
    val navigateToLoginScreen by registerViewModel.navigateToLoginScreen.collectAsStateWithLifecycle()
    val events = registerViewModel.events.collectAsStateWithLifecycle(initialValue = null)
    val context = LocalContext.current

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.White)){
            append("Already you have an account? ")
        }
        withStyle(style = SpanStyle(color = Color(0xFF4F71C7))){
            append("Log in")
        }
    }

    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(events.value) {
        events.value?.let { event ->
            when (event) {
                is UserEvent.Error -> {
                    println("Error: ${event.error.asString(context)}s")
                    snackBarHostState.showSnackbar(
                        event.error.asString(context),
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }
    }

    if (navigateToLoginScreen) {
        navHostController.navigate(Screens.LoginScreen.route) {
            popUpToRoute?.let { popUpToRoute ->
                popUpTo(popUpToRoute) { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            SnackbarHost(
                hostState = snackBarHostState, modifier = Modifier
                    .padding(top = 30.dp)
                    .zIndex(40f)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .align(Alignment.TopStart)
                    .height(200.dp)
            ) {
                TopHeaderSphere(200.dp)
                TopHeaderText(text = "Sign Up")
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .align(Alignment.BottomCenter)
            ) {

                BottomSphere(500.dp)

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .zIndex(3f)
                ) {

                    TextFieldLabel(text = "Email")

                    RegisterTextField(
                        text = emailText,
                        registerViewModel = registerViewModel,
                        placeholder = "Enter your email here...",
                        type = "email"
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    TextFieldLabel(text = "Username")

                    RegisterTextField(
                        text = usernameText,
                        registerViewModel = registerViewModel,
                        placeholder = "Enter a username here...",
                        type = "username"
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    TextFieldLabel(text = "Password")

                    RegisterTextField(
                        text = passwordText,
                        registerViewModel = registerViewModel,
                        placeholder = "Enter a password here...",
                        type = "password"
                    )

                    Spacer(modifier = Modifier.height(9.dp))

                    Spacer(modifier = Modifier.height(30.dp))

                    RegisterButton(registerViewModel = registerViewModel)

                    Spacer(modifier = Modifier.height(18.dp))

                    ClickableText(text = annotatedString) {
                        navHostController.navigate("LoginScreen")
                    }
                }
            }
        }

    }

}