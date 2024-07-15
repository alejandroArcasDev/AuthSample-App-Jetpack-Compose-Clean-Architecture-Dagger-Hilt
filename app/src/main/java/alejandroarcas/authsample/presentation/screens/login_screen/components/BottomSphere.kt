package alejandroarcas.authsample.presentation.screens.login_screen.components

import alejandroarcas.authsample.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BottomSphere(height: Dp){
    Image(
        painter = painterResource(id = R.drawable.bottom_figure),
        contentDescription = "Bottom Header Sphere",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
    )
}