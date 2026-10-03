package com.example.ui.result

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class QuestionResultItem(
    val id: Int,
    val qCode: String,
    val title: String,
    val awardedMarks: Int,
    val maxMarks: Int,
    val verifiedBy: String = "Verified by Teacher",
    val feedback: String = "All criteria correctly demonstrated."
)

data class StudentResultUiState(
    val studentName: String = "Marcus Vance",
    val rollNo: String = "1024",
    val className: String = "Class 10-A",
    val examName: String = "Physics Mid-Term",
    val refId: String = "Ref #TR-88214",
    val approvedBy: String = "Educator Sarah Jenkins",
    val approvedDate: String = "Oct 03, 2026",
    val score: Int = 20,
    val totalMarks: Int = 20,
    val scorePercent: String = "100%",
    val accuracy: String = "100%",
    val attemptedItems: String = "3/3 Items",
    val ocrQuality: String = "High (98%)",
    val expandedQuestionId: Int? = null,
    val questions: List<QuestionResultItem> = listOf(
        QuestionResultItem(
            id = 1,
            qCode = "Q1",
            title = "Short Answer: Newton's Law",
            awardedMarks = 5,
            maxMarks = 5,
            feedback = "Accurate and clear definition provided."
        ),
        QuestionResultItem(
            id = 2,
            qCode = "Q2",
            title = "Numerical: Force Calculation",
            awardedMarks = 10,
            maxMarks = 10,
            feedback = "Calculations and signs verified correctly. Step-by-step methodology followed."
        ),
        QuestionResultItem(
            id = 3,
            qCode = "Q3",
            title = "Long Answer: Conservation of Energy",
            awardedMarks = 5,
            maxMarks = 5,
            feedback = "Excellent conceptual explanation and diagram provided."
        )
    )
)

class StudentResultViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(StudentResultUiState())
    val uiState: StateFlow<StudentResultUiState> = _uiState.asStateFlow()

    fun toggleQuestionExpansion(id: Int) {
        _uiState.update { state ->
            val newExpandedId = if (state.expandedQuestionId == id) null else id
            state.copy(expandedQuestionId = newExpandedId)
        }
    }
}
