package com.alexzdns.books.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alexzdns.books.R
import com.alexzdns.books.domain.models.BookItem
import com.alexzdns.books.ui.theme.Typography
import com.alexzdns.books.ui.theme.darkGrey
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun DetailsBookView(
    book: BookItem
) {
    Column(
        Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {
        BookCover(book)

        Text(
            text = book.authors.joinToString(),
            Modifier
                .padding(top = 12.dp, start = 20.dp, end = 20.dp)
                .align(Alignment.CenterHorizontally),
            color = darkGrey,
            style = Typography.bodySmall
        )

        Text(
            text = book.title,
            Modifier
                .padding(top = 8.dp, start = 20.dp, end = 20.dp)
                .align(Alignment.CenterHorizontally),
            color = Color.Black,
            style = Typography.titleMedium
        )

        PublishedYear(book.publishedYear, Modifier.align(Alignment.CenterHorizontally))
        DescriptionBlock(book.description)
    }
}

@Composable
private fun BookCover(book: BookItem) {
    Card(
        Modifier
            .padding(top = 12.dp, start = 80.dp, end = 80.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(24.dp)
    ) {
        GlideImage(
            imageModel = book.imageUrl.orEmpty(),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.aspectRatio(2.0f / 3.0f),
        )
    }
}

@Composable
private fun PublishedYear(
    date: String?,
    modifier: Modifier
) {
    date ?: return
    Text(
        text = date,
        modifier = modifier.padding(top = 8.dp, start = 20.dp, end = 20.dp),
        color = darkGrey,
        style = Typography.bodySmall
    )
}

@Composable
private fun DescriptionBlock(description: String) {
    if (description.isBlank()) return

    Card(
        Modifier
            .padding(top = 22.dp)
            .fillMaxHeight(),
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(top = 20.dp, start = 20.dp, end = 20.dp)
                .fillMaxWidth(),
            text = stringResource(R.string.description_title),
            color = Color.Black,
            style = Typography.titleMedium
        )

        Text(
            modifier = Modifier
                .padding(top = 16.dp, start = 20.dp, end = 20.dp)
                .fillMaxWidth(),
            text = description,
            color = Color.Black,
            style = Typography.bodySmall,
            textAlign = TextAlign.Justify
        )

        Spacer(
            Modifier
                .fillMaxWidth()
                .height(20.dp)
        )
    }
}