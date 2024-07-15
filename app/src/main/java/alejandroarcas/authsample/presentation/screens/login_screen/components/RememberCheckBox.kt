package alejandroarcas.authsample.presentation.screens.login_screen.components

import alejandroarcas.authsample.presentation.screens.login_screen.LoginViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun RememberCheckBox(checkedState: Boolean, loginViewModel: LoginViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth(0.85f),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checkedState,
            onCheckedChange = { loginViewModel.onCheckedChange(it) })
        Text(
            text = "Remember me",
            modifier = Modifier.align(Alignment.CenterVertically),
            color = Color.White
        )
    }
}