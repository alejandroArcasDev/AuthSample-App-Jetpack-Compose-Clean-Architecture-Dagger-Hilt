package alejandroarcas.authsample.presentation.screens.profile_screen

import alejandroarcas.authsample.R
import alejandroarcas.authsample.presentation.screens.profile_screen.components.ProfileOptions
import alejandroarcas.authsample.presentation.screens.profile_screen.components.ProfileOptionsItem
import alejandroarcas.authsample.presentation.screens.profile_screen.components.TopSphereProfile
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(profileViewModel: ProfileViewModel, navHostController: NavHostController) {

    val userWantsToLogOut by profileViewModel.userWantsToLogout.collectAsStateWithLifecycle()
    val isLoggedOut by profileViewModel.isLoggedOut.collectAsStateWithLifecycle()

    if (userWantsToLogOut) {
        LaunchedEffect(true) {
            CoroutineScope(Dispatchers.IO).launch {
                profileViewModel.updateDatastore()
                profileViewModel.userWantsToLogout(false)
            }
        }
    }
    if (isLoggedOut){
        profileViewModel.onLogOutChange(false)
        navHostController.navigate("LoginScreen") {
            popUpTo("LoginScreen") { inclusive = true }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)

        ) {
            //TopSphereProfile()
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "Profile",
                    fontSize = 30.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }

        Spacer(modifier = Modifier.height(35.dp))

        Text(
            text = "Username",
            color = Color(0xFF172037),
            fontSize = 25.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(text = "example@yourdomain.com", fontSize = 15.sp)

        Spacer(modifier = Modifier.height(30.dp))
        Column {
            ProfileOptionsItem(
                text = "Edit Profile",
                icon = R.drawable.ic_edit,
                onClick = profileViewModel::onClickEditProfile
            )
            Spacer(modifier = Modifier.height(20.dp))
            ProfileOptionsItem(
                text = "Change Password",
                icon = R.drawable.ic_password,
                onClick = profileViewModel::onClickChangePassword
            )
            Spacer(modifier = Modifier.height(20.dp))
            ProfileOptionsItem(
                text = "Log Out Profile",
                icon = R.drawable.ic_logout,
                onClick = { profileViewModel.userWantsToLogout(true) }
            )
            Spacer(modifier = Modifier.height(20.dp))
            ProfileOptionsItem(
                text = "Edit Profile",
                icon = R.drawable.ic_delete,
                onClick = profileViewModel::onClickDeleteAcc
            )
        }
    }
}



