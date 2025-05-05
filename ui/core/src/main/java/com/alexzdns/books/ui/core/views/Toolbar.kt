package com.alexzdns.books.ui.core.views

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alexzdns.books.ui.core.theme.TypographyApp

@Composable
fun Toolbar(
    onBackClick: () -> Unit,
    @StringRes title: Int,
) {
    Box(Modifier.fillMaxWidth()) {
        Image(
            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .clickable(onClick = onBackClick)
                .padding(vertical = 12.dp, horizontal = 20.dp)

        )

        Text(
            text = stringResource(title),
            color = Color.Black,
            style = TypographyApp.headlineLarge,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}