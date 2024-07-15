package alejandroarcas.authsample.presentation.screens.register_screen.components

import alejandroarcas.authsample.presentation.screens.login_screen.LoginViewModel
import alejandroarcas.authsample.presentation.screens.register_screen.RegisterViewModel
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun RegisterTextField(text: String, registerViewModel: RegisterViewModel, placeholder: String, type: String) {
    TextField(
        modifier = Modifier
            .height(50.dp)
            .width(LocalConfiguration.current.screenWidthDp.dp * 0.85f),
        shape = RoundedCornerShape(16.dp),
        value = text,
        onValueChange = {
            when(type) {
                "email" -> registerViewModel.onChangeEmail(it)
                "username" -> registerViewModel.onChangeUsername(it)
                "password" ->  registerViewModel.onChangePassword(it)
            }
        },
        placeholder = { Text(text = placeholder) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFF2F3850),
            unfocusedContainerColor = Color(0xFF2F3850),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedTextColor = Color.Gray,
            unfocusedTextColor = Color.Gray,
            cursorColor = Color.White
        )
    )
}