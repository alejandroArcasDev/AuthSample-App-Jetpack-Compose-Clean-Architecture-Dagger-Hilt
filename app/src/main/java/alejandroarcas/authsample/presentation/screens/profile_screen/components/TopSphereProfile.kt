package alejandroarcas.authsample.presentation.screens.profile_screen.components

import alejandroarcas.authsample.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun TopSphereProfile() {
    Image(
        painter = painterResource(id = R.drawable.bottom_figure),
        contentDescription = "Bottom Header Sphere",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer {
                rotationZ = 180f
            }
            .height(250.dp)
    )
}