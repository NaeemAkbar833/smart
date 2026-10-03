package com.example.ui.evaluation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class AiEvaluationReviewUiState(
    val studentName: String = "Marcus Vance",
    val rollNo: String = "#24-A",
    val examName: String = "Physics Mid-Term",
    val selectedFilterTab: Int = 0,
    val q1Score: Int = 5,
    val q1MaxScore: Int = 5,
    val q2Score: Int = 10,
    val q2MaxScore: Int = 10,
    val q1Feedback: String = "Accurate and clear definition provided.",
    val q2Feedback: String = "Excellent work showing step-by-step working.",
    val isTeacherValidated: Boolean = true
)

class AiEvaluationReviewViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AiEvaluationReviewUiState())
    val uiState: StateFlow<AiEvaluationReviewUiState> = _uiState.asStateFlow()

    fun onFilterTabSelect(tab: Int) {
        _uiState.update { it.copy(selectedFilterTab = tab) }
    }

    fun updateQ1Score(delta: Int) {
        _uiState.update { state ->
            val newScore = (state.q1Score + delta).coerceIn(0, state.q1MaxScore)
            state.copy(q1Score = newScore)
        }
    }

    fun updateQ2Score(delta: Int) {
        _uiState.update { state ->
            val newScore = (state.q2Score + delta).coerceIn(0, state.q2MaxScore)
            state.copy(q2Score = newScore)
        }
    }

    fun updateQ1Feedback(text: String) {
        _uiState.update { it.copy(q1Feedback = text) }
    }

    fun updateQ2Feedback(text: String) {
        _uiState.update { it.copy(q2Feedback = text) }
    }

    fun toggleValidation() {
        _uiState.update { it.copy(isTeacherValidated = !it.isTeacherValidated) }
    }
}
