package com.example.ui.questions

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class QuestionItem(
    val id: Int,
    val number: Int,
    val prompt: String,
    val type: String,
    val marks: String,
    val isDropdownExpanded: Boolean = false
)

data class QuestionsUiState(
    val questions: List<QuestionItem> = listOf(
        QuestionItem(
            id = 1,
            number = 1,
            prompt = "State and derive Newton's Second Law of Motion in terms of momentum variation over unit time.",
            type = "Short Answer",
            marks = "5"
        ),
        QuestionItem(
            id = 2,
            number = 2,
            prompt = "A 1200kg vehicle decelerates from 25 m/s to rest in 5 seconds. Calculate the average net braking force required.",
            type = "Numerical",
            marks = "10"
        ),
        QuestionItem(
            id = 3,
            number = 3,
            prompt = "Describe the principle of conservation of angular momentum. Provide two practical examples observed in astronomical systems.",
            type = "Long Answer",
            marks = "15"
        )
    ),
    val isEmptyState: Boolean = false,
    val questionTypes: List<String> = listOf("Short Answer", "Numerical", "Long Answer", "Multiple Choice")
)

class QuestionsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(QuestionsUiState())
    val uiState: StateFlow<QuestionsUiState> = _uiState.asStateFlow()

    private var nextId = 4

    fun addQuestion() {
        _uiState.update { state ->
            val newNum = state.questions.size + 1
            val newItem = QuestionItem(
                id = nextId++,
                number = newNum,
                prompt = "Enter question prompt or mathematical formula...",
                type = "Short Answer",
                marks = "5"
            )
            state.copy(
                questions = state.questions + newItem,
                isEmptyState = false
            )
        }
    }

    fun deleteQuestion(id: Int) {
        _uiState.update { state ->
            val updated = state.questions.filter { it.id != id }
                .mapIndexed { index, item -> item.copy(number = index + 1) }
            state.copy(
                questions = updated,
                isEmptyState = updated.isEmpty()
            )
        }
    }

    fun duplicateQuestion(id: Int) {
        _uiState.update { state ->
            val itemToDup = state.questions.find { it.id == id } ?: return@update state
            val index = state.questions.indexOf(itemToDup)
            val newItem = itemToDup.copy(id = nextId++)
            val list = state.questions.toMutableList()
            list.add(index + 1, newItem)
            val renumbered = list.mapIndexed { i, item -> item.copy(number = i + 1) }
            state.copy(questions = renumbered)
        }
    }

    fun updateQuestionPrompt(id: Int, newPrompt: String) {
        _uiState.update { state ->
            val updated = state.questions.map {
                if (it.id == id) it.copy(prompt = newPrompt) else it
            }
            state.copy(questions = updated)
        }
    }

    fun updateQuestionType(id: Int, newType: String) {
        _uiState.update { state ->
            val updated = state.questions.map {
                if (it.id == id) it.copy(type = newType, isDropdownExpanded = false) else it
            }
            state.copy(questions = updated)
        }
    }

    fun toggleQuestionDropdown(id: Int, expanded: Boolean) {
        _uiState.update { state ->
            val updated = state.questions.map {
                if (it.id == id) it.copy(isDropdownExpanded = expanded) else it
            }
            state.copy(questions = updated)
        }
    }

    fun updateQuestionMarks(id: Int, newMarks: String) {
        _uiState.update { state ->
            val updated = state.questions.map {
                if (it.id == id) it.copy(marks = newMarks) else it
            }
            state.copy(questions = updated)
        }
    }

    fun toggleEmptyState() {
        _uiState.update { it.copy(isEmptyState = !it.isEmptyState) }
    }
}
