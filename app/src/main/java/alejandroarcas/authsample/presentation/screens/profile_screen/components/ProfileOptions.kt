package alejandroarcas.authsample.presentation.screens.profile_screen.components

import alejandroarcas.authsample.R
import alejandroarcas.authsample.presentation.screens.profile_screen.ProfileViewModel
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun ProfileOptions(i: Int, profileViewModel: ProfileViewModel, navHostController: NavHostController) {
    when (i) {
        0 -> {
            ProfileOptionsItem(
                text = "Edit Profile",
                icon = R.drawable.ic_edit,
                onClick = profileViewModel::onClickEditProfile
            )
        }

        1 -> {
            ProfileOptionsItem(
                text = "Change Password",
                icon = R.drawable.ic_password,
                onClick = profileViewModel::onClickChangePassword
            )
        }

        2 -> {
            ProfileOptionsItem(
                text = "Log Out",
                icon = R.drawable.ic_logout,
                onClick = { profileViewModel.userWantsToLogout(true) }
            )
        }

        3 -> {
            ProfileOptionsItem(
                text = "Delete Account",
                icon = R.drawable.ic_delete,
                onClick = profileViewModel::onClickDeleteAcc
            )
        }
    }
}