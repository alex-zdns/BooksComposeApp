package com.alexzdns.books.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alexzdns.books.R
import com.alexzdns.books.ui.theme.Typography

@Composable
fun ErrorView(
    onRetry: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                text = stringResource(R.string.error_message),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                style = Typography.titleLarge,
                textAlign = TextAlign.Center
            )
            Button(
                onClick = onRetry,
                Modifier
                    .padding(top = 12.dp)
                    .align(Alignment.CenterHorizontally),
                contentPadding = PaddingValues(horizontal = 22.dp, vertical = 10.dp),
            ) {
                Text(
                    text = stringResource(R.string.retry),
                    style = Typography.bodyMedium
                )
            }
        }
    }
}

@Preview
@Composable
fun ErrorViewPreview() {
    Surface(Modifier.fillMaxSize()) {
        ErrorView(onRetry = {})
    }
}