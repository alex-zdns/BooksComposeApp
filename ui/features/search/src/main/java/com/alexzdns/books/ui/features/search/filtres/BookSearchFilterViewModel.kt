package com.alexzdns.books.ui.features.search.filtres

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.alexzdns.books.domain.models.BookSortType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class BookSearchFilterViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val initState = BookSearchFilterState(
        selectedSortType = savedStateHandle.get<BookSortType>(SORT_TYPE_KEY) ?: BookSortType.NONE
    )

    private val _state = MutableStateFlow<BookSearchFilterState>(initState)
    val state = _state.asStateFlow()

    fun onSortTypeClick(sortType: BookSortType) {
        val state = _state.value
        val newSortType = if (state.selectedSortType == sortType) {
            BookSortType.NONE
        } else {
            sortType
        }

        _state.value = state.copy(
            selectedSortType = newSortType,
            isEnabledApplyButton = isEnabledApplyButton(selectedSortType = newSortType)
        )
    }

    private fun isEnabledApplyButton(selectedSortType: BookSortType?): Boolean {
        return initState.selectedSortType != selectedSortType
    }
}
