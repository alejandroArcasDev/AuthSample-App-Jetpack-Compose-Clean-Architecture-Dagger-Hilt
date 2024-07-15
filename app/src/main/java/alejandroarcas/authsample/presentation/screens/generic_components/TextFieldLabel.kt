package alejandroarcas.authsample.presentation.screens.generic_components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TextFieldLabel(text: String) {
    Column(
        Modifier.fillMaxWidth(0.85f)
    ) {
        Text(
            text = text,
            modifier = Modifier.align(Alignment.Start),
            color = Color.White
        )
        Spacer(modifier = Modifier.height(7.dp))
    }
}