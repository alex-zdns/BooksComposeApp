package com.alexzdns.books.ui.features.history.models

import com.alexzdns.books.domain.models.BookWithViewTime
import java.time.LocalDate

data class HistoryItem(
    val day: LocalDate,
    val books: List<BookWithViewTime>,
)