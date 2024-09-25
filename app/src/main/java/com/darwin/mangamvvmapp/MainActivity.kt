package com.darwin.mangamvvmapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.darwin.mangamvvmapp.navigation.Navigator
import com.darwin.mangamvvmapp.ui.theme.MangaMvvmAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MangaMvvmAppTheme {
                Navigator()
            }
        }
    }
}
