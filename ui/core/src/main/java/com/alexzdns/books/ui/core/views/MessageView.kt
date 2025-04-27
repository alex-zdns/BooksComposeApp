package com.alexzdns.books.ui.core.views

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alexzdns.books.ui.core.R
import com.alexzdns.books.ui.core.theme.Typography

@Composable
fun MessageView(@StringRes messageId: Int) {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(messageId),
            color = Color.Black,
            style = Typography.titleLarge,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
    }
}

@Preview
@Composable
fun MessageViewPreview() {
    Surface(Modifier.fillMaxSize()) {
        MessageView(R.string.retry)
    }
}