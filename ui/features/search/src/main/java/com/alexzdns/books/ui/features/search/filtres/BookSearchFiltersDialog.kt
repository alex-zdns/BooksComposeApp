package com.alexzdns.books.ui.features.search.filtres

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alexzdns.books.domain.models.BookSortType
import com.alexzdns.books.ui.core.theme.TypographyApp
import com.alexzdns.books.ui.core.theme.blue
import com.alexzdns.books.ui.core.theme.filterBorder
import com.alexzdns.books.ui.features.search.R
import com.alexzdns.books.ui.features.search.extensions.getNameRes
import com.alexzdns.books.ui.core.R as UiCoreR

@Composable
fun SearchFiltersDialog(
    onDismiss: () -> Unit,
    viewModel: BookSearchFilterViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.2f))
            .clickable(onClick = onDismiss, indication = null, interactionSource = null)
    ) {
        Surface(
            shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp),
            color = Color.White
        ) {
            FilterDialogInternal(
                state = state.value,
                onDismiss = onDismiss,
                onSortTypeClick = viewModel::onSortTypeClick
            )
        }
    }
}

@Composable
private fun FilterDialogInternal(
    onDismiss: () -> Unit,
    onSortTypeClick: (BookSortType) -> Unit,
    state: BookSearchFilterState,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        FilterToolbar(onCloseClick = onDismiss)

        Text(
            text = stringResource(R.string.sort_title),
            style = TypographyApp.titleMedium,
            modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 24.dp)
        )

        Row(
            modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            state.sortTypes.forEach {
                val isSelected = it == state.selectedSortType
                FilterItem(
                    type = it,
                    isSelected = isSelected,
                    onTypeClick = onSortTypeClick
                )
            }
        }

        Button(
            onClick = {
                /*onApplyClick.invoke(state.selectedSortType)*/
            },
            enabled = state.isEnabledApplyButton,
            modifier = Modifier
                .padding(top = 30.dp, bottom = 26.dp)
                .align(Alignment.CenterHorizontally),
            contentPadding = PaddingValues(horizontal = 48.dp, vertical = 10.dp),
        ) {
            Text(
                text = stringResource(R.string.apply_button),
                style = TypographyApp.bodyMedium
            )
        }
    }
}

@Composable
private fun FilterToolbar(onCloseClick: () -> Unit) {
    Box(Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.filters_title),
            color = Color.Black,
            style = TypographyApp.headlineLarge,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(vertical = 15.dp)
        )

        Image(
            painter = painterResource(id = UiCoreR.drawable.ic_close_24),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clickable(onClick = onCloseClick)
                .padding(vertical = 14.dp, horizontal = 20.dp)

        )
    }
}

@Composable
private fun FilterItem(
    type: BookSortType,
    isSelected: Boolean,
    onTypeClick: (BookSortType) -> Unit
) {
    val borderColor = if (isSelected) blue else filterBorder
    val radius = 16.dp
    Text(
        text = stringResource(type.getNameRes()),
        style = TypographyApp.bodySmall,
        modifier = Modifier
            .clickable(
                role = Role.RadioButton
            ) {
                onTypeClick.invoke(type)
            }
            .border(1.dp, color = borderColor, shape = RoundedCornerShape(radius))
            .padding(vertical = 10.dp, horizontal = 12.dp)
    )
}