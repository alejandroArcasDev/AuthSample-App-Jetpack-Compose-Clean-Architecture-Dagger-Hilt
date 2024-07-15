package alejandroarcas.authsample.presentation.screens.generic_components

import alejandroarcas.authsample.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun TopHeaderSphere(height: Dp) {
    Image(
        painter = painterResource(id = R.drawable.top_figure),
        contentDescription = "Top Header Sphere",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
    )
}
