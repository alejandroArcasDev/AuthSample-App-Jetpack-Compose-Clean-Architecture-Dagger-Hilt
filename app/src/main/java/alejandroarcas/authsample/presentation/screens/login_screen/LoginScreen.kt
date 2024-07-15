package alejandroarcas.authsample.presentation.screens.login_screen

import alejandroarcas.authsample.presentation.model.UserEvent
import alejandroarcas.authsample.presentation.screens.generic_components.TextFieldLabel
import alejandroarcas.authsample.presentation.screens.generic_components.TopHeaderSphere
import alejandroarcas.authsample.presentation.screens.generic_components.TopHeaderText
import alejandroarcas.authsample.presentation.screens.login_screen.components.BottomSphere
import alejandroarcas.authsample.presentation.screens.login_screen.components.LoginButton
import alejandroarcas.authsample.presentation.screens.login_screen.components.LoginTextField
import alejandroarcas.authsample.presentation.screens.login_screen.components.RememberCheckBox
import android.annotation.SuppressLint
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
import androidx.compose.material3.Text
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LoginScreen(loginViewModel: LoginViewModel, navHostController: NavHostController) {

    val userNameText by loginViewModel.userNameText.collectAsStateWithLifecycle()
    val passwordText by loginViewModel.passwordText.collectAsStateWithLifecycle()
    val checkedState by loginViewModel.checkedState.collectAsStateWithLifecycle()
    val navigateToProfileScreen by loginViewModel.navigateToProfileScreen.collectAsStateWithLifecycle()
    val events = loginViewModel.events.collectAsStateWithLifecycle(initialValue = null)
    val context = LocalContext.current

    val snackBarHostState = remember { SnackbarHostState() }

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.White)){
            append("Don't have an account? ")
        }
        withStyle(style = SpanStyle(color = Color(0xFF4F71C7))){
            append("Sign Up")
        }
    }

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
                    .height(240.dp)
            ) {
                TopHeaderSphere(240.dp)

                TopHeaderText(text = "Log In")
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp)
                    .align(Alignment.BottomCenter)
            ) {

                BottomSphere(450.dp)

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .zIndex(3f)
                ) {

                    TextFieldLabel(text = "Username")

                    LoginTextField(
                        text = userNameText,
                        loginViewModel = loginViewModel,
                        placeholder = "Enter your username here..."
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    TextFieldLabel(text = "Password")

                    LoginTextField(
                        text = passwordText,
                        loginViewModel = loginViewModel,
                        placeholder = "Enter your password here..."
                    )

                    Spacer(modifier = Modifier.height(9.dp))

                    RememberCheckBox(checkedState, loginViewModel)

                    Spacer(modifier = Modifier.height(25.dp))

                    LoginButton(loginViewModel)
                    
                    Spacer(modifier = Modifier.height(18.dp))

                    if (navigateToProfileScreen){
                        navHostController.navigate("ProfileScreen")
                    }
                    
                    ClickableText(text = annotatedString) {
                        navHostController.navigate("RegisterScreen")
                    }
                }
            }
        }
    }
}



