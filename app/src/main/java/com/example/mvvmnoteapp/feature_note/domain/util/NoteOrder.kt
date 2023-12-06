package com.example.mvvmnoteapp.feature_note.domain.util

sealed class NoteOrder(val orderType: OrderType) {
    class ByTitle(orderType: OrderType): NoteOrder(orderType)
    class ByDate(orderType: OrderType): NoteOrder(orderType)
    class ByColor(orderType: OrderType): NoteOrder(orderType)

    fun copy(orderType: OrderType): NoteOrder {
        return when(this) {
            is ByTitle -> ByTitle(orderType)
            is ByDate -> ByDate(orderType)
            is ByColor -> ByColor(orderType)
        }
    }
}
