package com.example.ui.exam

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class CreateExamUiState(
    val examTitle: String = "",
    val examType: String = "",
    val examDate: String = "",
    val description: String = "",
    val isTypeDropdownExpanded: Boolean = false,
    val examTypes: List<String> = listOf("Midterm Examination", "Final Examination", "Quiz / Test", "Unit Assessment", "Assignment")
)

class CreateExamViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CreateExamUiState())
    val uiState: StateFlow<CreateExamUiState> = _uiState.asStateFlow()

    fun onTitleChange(newTitle: String) {
        if (newTitle.length <= 80) {
            _uiState.update { it.copy(examTitle = newTitle) }
        }
    }

    fun onExamTypeSelect(type: String) {
        _uiState.update { it.copy(examType = type, isTypeDropdownExpanded = false) }
    }

    fun toggleTypeDropdown(expanded: Boolean) {
        _uiState.update { it.copy(isTypeDropdownExpanded = expanded) }
    }

    fun onExamDateChange(newDate: String) {
        _uiState.update { it.copy(examDate = newDate) }
    }

    fun onDescriptionChange(newDesc: String) {
        _uiState.update { it.copy(description = newDesc) }
    }
}
