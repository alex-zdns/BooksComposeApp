package com.alexzdns.books.ui.features.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.alexzdns.books.ui.core.theme.TypographyApp
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private val localDateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy")

@Composable
fun DayStickyItem(
    item: LocalDate,
) {
    Text(
        text = localDateFormatter.format(item),
        style = TypographyApp.headlineLarge,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 10.dp, horizontal = 16.dp)

    )
}