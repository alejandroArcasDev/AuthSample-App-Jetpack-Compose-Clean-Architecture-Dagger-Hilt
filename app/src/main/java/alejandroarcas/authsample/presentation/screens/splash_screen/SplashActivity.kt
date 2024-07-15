package alejandroarcas.authsample.presentation.screens.splash_screen

import alejandroarcas.authsample.MainActivity
import alejandroarcas.authsample.R
import alejandroarcas.authsample.presentation.ui.AuthSampleTheme
import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()

        setContent {
            AuthSampleTheme {

                val splashViewModel: SplashViewModel = hiltViewModel()

                val token by splashViewModel.token.collectAsStateWithLifecycle()

                Box(
                    Modifier.fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.Center)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth(0.5f)
                        )

                        LaunchedEffect(key1 = true) {
                            delay(2000)
                            val intent = Intent(this@SplashActivity, MainActivity::class.java)
                            val bundle = ActivityOptions.makeCustomAnimation(
                                this@SplashActivity,
                                R.anim.fade_in,
                                R.anim.fade_out
                            ).toBundle()
                            bundle.putBoolean("isLogged", token != "")
                            intent.putExtras(bundle)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
            }
        }
    }
}