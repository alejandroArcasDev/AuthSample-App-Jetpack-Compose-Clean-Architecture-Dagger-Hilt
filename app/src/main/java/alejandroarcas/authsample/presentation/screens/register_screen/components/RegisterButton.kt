package alejandroarcas.authsample.presentation.screens.register_screen.components

import alejandroarcas.authsample.presentation.screens.register_screen.RegisterViewModel
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun RegisterButton(registerViewModel: RegisterViewModel) {

    val isLoading by registerViewModel.isLoading.collectAsStateWithLifecycle()

    ElevatedButton(
        onClick = { registerViewModel.onRegisterClick() },
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .height(LocalConfiguration.current.screenHeightDp.dp / 16),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF3F64C5),
            contentColor = Color.White
        )
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .height(20.dp)
                    .width(20.dp),
                color = Color.White
            )
        } else {
            Text(text = "Sign Up", fontSize = 16.sp)
        }
    }
}