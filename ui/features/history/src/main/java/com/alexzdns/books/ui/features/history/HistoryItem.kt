package com.alexzdns.books.ui.features.history

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alexzdns.books.domain.models.BookItem
import com.alexzdns.books.domain.models.BookWithViewTime
import com.alexzdns.books.ui.core.theme.TypographyApp
import com.alexzdns.books.ui.core.theme.darkGrey
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun HistoryItem(
    item: BookWithViewTime,
    onBookClick: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable { onBookClick.invoke(item.book.id) }
    ) {
        BookCover(item.book)
        Column(
            modifier = Modifier.padding(start = 8.dp)
        ) {
            val book = item.book
            Text(
                text = book.title,
                color = Color.Black,
                style = TypographyApp.bodySmall
            )

            Text(
                text = if (book.authors.isEmpty())
                    stringResource(com.alexzdns.books.ui.core.R.string.empty_authors)
                else
                    book.authors.joinToString(),
                Modifier.padding(top = 2.dp),
                color = darkGrey,
                style = TypographyApp.bodySmall
            )

            PublishedYear(book.publishedYear)
        }
    }
}

@Composable
private fun BookCover(book: BookItem) {
    Card(
        Modifier.width(80.dp), shape = RoundedCornerShape(16.dp)
    ) {
        GlideImage(
            imageModel = book.thumbnailUrl.orEmpty(),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2.0f / 3.0f),
        )
    }
}

/*@Composable
private fun BookInfo(item: BookWithViewTime) {


}*/

@Composable
private fun PublishedYear(
    date: String?,
) {
    date ?: return
    Text(
        text = date,
        modifier = Modifier.padding(top = 2.dp),
        color = darkGrey,
        style = TypographyApp.labelMedium
    )
}