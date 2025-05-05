package com.alexzdns.books.ui.features.history

import androidx.lifecycle.ViewModel
import com.alexzdns.books.domain.models.BookWithViewTime
import com.alexzdns.books.domain.repository.HistorySeenBooksRepository
import com.alexzdns.books.ui.features.history.models.HistoryItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class HistoryListViewModel @Inject constructor(
    private val repository: HistorySeenBooksRepository
) : ViewModel() {
    val flow = repository.getBooksHistory().map { list ->
        mapItems(list)
    }

    private fun mapItems(items: List<BookWithViewTime>): List<HistoryItem> {
        if (items.isEmpty()) return emptyList()

        var currentDay: LocalDate = items.first().timestamp.toLocalDate()
        val result = mutableListOf<HistoryItem>()

        val temp = mutableListOf<BookWithViewTime>()

        items.forEach {
            if (!currentDay.isSameDay(it.timestamp)) {
                currentDay = it.timestamp.toLocalDate()
                result.add(HistoryItem(currentDay, temp.toList()))
                temp.clear()
            }

            temp.add(it)
        }

        return result
    }

    private fun LocalDate.isSameDay(time: LocalDateTime): Boolean = this.year == time.year && this.dayOfYear == time.dayOfYear
}