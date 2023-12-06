package com.example.mvvmnoteapp.feature_note.domain.use_case

import com.example.mvvmnoteapp.feature_note.domain.model.Note
import com.example.mvvmnoteapp.feature_note.domain.repository.NoteRepository
import com.example.mvvmnoteapp.feature_note.domain.util.NoteOrder
import com.example.mvvmnoteapp.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCase(
    private val repository: NoteRepository
) {

    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.ByDate(OrderType.Descending)
    ): Flow<List<Note>> {
        return repository.getNotes().map {  notes ->
            when(noteOrder.orderType) {
                is OrderType.Ascending -> {
                    when(noteOrder) {
                        is NoteOrder.ByTitle -> notes.sortedBy { it.title.lowercase() }
                        is NoteOrder.ByDate -> notes.sortedBy { it.timestamp }
                        is NoteOrder.ByColor -> notes.sortedBy { it.color }
                    }
                }
                is OrderType.Descending -> {
                    when(noteOrder) {
                        is NoteOrder.ByTitle -> notes.sortedByDescending { it.title.lowercase() }
                        is NoteOrder.ByDate -> notes.sortedByDescending { it.timestamp }
                        is NoteOrder.ByColor -> notes.sortedByDescending { it.color }
                    }
                }
            }
        }
    }
}