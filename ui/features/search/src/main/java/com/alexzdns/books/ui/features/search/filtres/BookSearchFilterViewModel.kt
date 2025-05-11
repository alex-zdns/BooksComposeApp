package com.alexzdns.books.ui.features.search.filtres

import androidx.lifecycle.ViewModel
import com.alexzdns.books.domain.models.BookSortType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class BookSearchFilterViewModel @Inject constructor() : ViewModel() {

    private var initState = BookSearchFilterState()

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
