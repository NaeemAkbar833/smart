package com.example.ui.results

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class StudentResultSummaryItem(
    val id: Int,
    val studentName: String,
    val rollNo: String,
    val studentId: String,
    val totalMarks: Int,
    val obtainedMarks: Int,
    val percent: String,
    val status: String // "Completed", "Awaiting Selection"
)

data class ResultsUiState(
    val selectedExam: String = "Physics Mid-Term Exam",
    val selectedClass: String = "Class 10-A",
    val selectedSubject: String = "Physics",
    val isResultGenerated: Boolean = true,
    val students: List<StudentResultSummaryItem> = listOf(
        StudentResultSummaryItem(
            id = 1,
            studentName = "Marcus Vance",
            rollNo = "1024",
            studentId = "STU-88214",
            totalMarks = 20,
            obtainedMarks = 20,
            percent = "100%",
            status = "Completed"
        ),
        StudentResultSummaryItem(
            id = 2,
            studentName = "Emily Rodriguez",
            rollNo = "1025",
            studentId = "STU-88215",
            totalMarks = 20,
            obtainedMarks = 18,
            percent = "90%",
            status = "Completed"
        ),
        StudentResultSummaryItem(
            id = 3,
            studentName = "Aarav Patel",
            rollNo = "1026",
            studentId = "STU-88216",
            totalMarks = 20,
            obtainedMarks = 17,
            percent = "85%",
            status = "Awaiting Selection"
        )
    )
)

class ResultsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ResultsUiState())
    val uiState: StateFlow<ResultsUiState> = _uiState.asStateFlow()

    fun onSelectExam(exam: String) {
        _uiState.update { it.copy(selectedExam = exam) }
    }

    fun onSelectClass(className: String) {
        _uiState.update { it.copy(selectedClass = className) }
    }

    fun onSelectSubject(subject: String) {
        _uiState.update { it.copy(selectedSubject = subject) }
    }

    fun clearCriteria() {
        _uiState.update {
            it.copy(
                selectedExam = "",
                selectedClass = "",
                selectedSubject = "",
                isResultGenerated = false
            )
        }
    }

    fun generateClassResult() {
        _uiState.update { it.copy(isResultGenerated = true) }
    }
}
