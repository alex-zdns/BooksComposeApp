package com.alexzdns.books

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.alexzdns.books.ui.ScreenHost
import com.alexzdns.books.ui.core.theme.BooksComposeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BooksComposeAppTheme {
                ScreenHost()
            }
        }
    }
}
