package com.sameh.estatehub.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.sameh.estatehub.presentation.ui.navigation.AppNavigationGraph
import com.sameh.estatehub.presentation.ui.theme.EstateHubTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            EstateHubTheme {
                AppEntryPoint()
            }
        }
    }
}


@Composable
fun AppEntryPoint() {
    AppNavigationGraph()
}