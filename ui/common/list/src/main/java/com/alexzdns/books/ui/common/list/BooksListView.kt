package com.alexzdns.books.ui.common.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.alexzdns.books.ui.core.R
import com.alexzdns.books.ui.core.models.BookItemUi
import com.alexzdns.books.ui.core.theme.Typography
import com.alexzdns.books.ui.core.theme.darkGrey
import com.alexzdns.books.ui.core.theme.lightGrey
import com.alexzdns.books.ui.core.theme.red
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun BooksListView(
    books: List<BookItemUi>,
    onBookClick: (String) -> Unit,
    onFavorite: (String) -> Unit,
) {
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
                onFavorite = onFavorite
            )
        }
    }
}

@Composable
private fun BookItemView(
    item: BookItemUi,
    onItemClick: (String) -> Unit,
    onFavorite: (String) -> Unit,
) {
    val book = item.book

    Column(
        Modifier
            .fillMaxWidth()
            .clickable {
                onItemClick.invoke(book.id)
            }
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
        ) {
            Box {
                GlideImage(
                    imageModel = book.thumbnailUrl.orEmpty(),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.aspectRatio(154.0f / 230.0f),
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_favorite),
                    tint = if (item.isFavorite) red else lightGrey,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 6.dp, end = 9.dp)
                        .shadow(shape = ShapeDefaults.ExtraLarge, elevation = 10.dp)
                        .background(color = Color.White, shape = ShapeDefaults.ExtraLarge)
                        .padding(4.dp)
                        .clickable {
                            onFavorite(book.id)
                        }
                        .align(Alignment.TopEnd)
                )
            }
        }

        Text(
            text = book.authors.joinToString(),
            Modifier.padding(top = 8.dp),
            color = darkGrey,
            style = Typography.bodySmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = book.title,
            Modifier.padding(top = 4.dp),
            color = Color.Black,
            style = Typography.bodySmall,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}
