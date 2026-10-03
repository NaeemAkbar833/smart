package com.example.ui.classsetup

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ClassSetupUiState(
    val academicSession: String = "",
    val semesterGrade: String = "",
    val shiftSection: String = "",
    val subjectName: String = "",
    val totalExamMarks: String = "",
    val isShiftDropdownExpanded: Boolean = false,
    val shiftOptions: List<String> = listOf(
        "Morning Shift / Section A",
        "Day Shift / Section B",
        "Evening Shift / Section C",
        "Self-Paced / Section D"
    )
)

class ClassSetupViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ClassSetupUiState())
    val uiState: StateFlow<ClassSetupUiState> = _uiState.asStateFlow()

    fun onAcademicSessionChange(session: String) {
        _uiState.update { it.copy(academicSession = session) }
    }

    fun onSemesterGradeChange(semester: String) {
        _uiState.update { it.copy(semesterGrade = semester) }
    }

    fun onShiftSectionSelect(shift: String) {
        _uiState.update { it.copy(shiftSection = shift, isShiftDropdownExpanded = false) }
    }

    fun toggleShiftDropdown(expanded: Boolean) {
        _uiState.update { it.copy(isShiftDropdownExpanded = expanded) }
    }

    fun onSubjectNameChange(subject: String) {
        _uiState.update { it.copy(subjectName = subject) }
    }

    fun onTotalExamMarksChange(marks: String) {
        _uiState.update { it.copy(totalExamMarks = marks) }
    }
}
