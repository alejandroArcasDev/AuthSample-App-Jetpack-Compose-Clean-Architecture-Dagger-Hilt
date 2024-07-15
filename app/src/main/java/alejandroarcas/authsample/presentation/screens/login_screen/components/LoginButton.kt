package alejandroarcas.authsample.presentation.screens.login_screen.components

import alejandroarcas.authsample.presentation.screens.login_screen.LoginViewModel
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginButton(loginViewModel: LoginViewModel) {
    ElevatedButton(
        onClick = { loginViewModel.onLoginClick() },
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .height(LocalConfiguration.current.screenHeightDp.dp / 16),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF3F64C5),
            contentColor = Color.White
        )
    ) {
        Text(text = "Log in", fontSize = 16.sp)
    }
}