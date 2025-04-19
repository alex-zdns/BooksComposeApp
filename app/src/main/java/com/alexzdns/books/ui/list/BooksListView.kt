package com.alexzdns.books.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.alexzdns.books.domain.models.BookItem
import com.alexzdns.books.ui.theme.Typography
import com.alexzdns.books.ui.theme.darkGrey
import com.skydoves.landscapist.glide.GlideImage
import androidx.compose.foundation.lazy.grid.items

@Composable
fun BooksListView(books: List<BookItem>, onBookClick: (String) -> Unit) {
    LazyVerticalGrid(
        GridCells.Fixed(2),
        contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 12.dp, bottom = 12.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        items(books) {
            BookItemView(
                item = it,
                onItemClick = onBookClick,
            )
        }
    }
}

@Composable
private fun BookItemView(item: BookItem, onItemClick: (String) -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .clickable {
                onItemClick.invoke(item.id)
            }
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
        ) {
            GlideImage(
                imageModel = item.imageUrl.orEmpty(),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.aspectRatio(154.0f / 230.0f),
            )
        }

        Text(
            text = item.authors.joinToString(),
            Modifier.padding(top = 8.dp),
            color = darkGrey,
            style = Typography.bodySmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = item.title,
            Modifier.padding(top = 4.dp),
            color = Color.Black,
            style = Typography.bodySmall,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}
