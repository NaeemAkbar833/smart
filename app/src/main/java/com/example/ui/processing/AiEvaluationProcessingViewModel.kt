package com.example.ui.processing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ProcessingStageItem(
    val stageNumber: Int,
    val title: String,
    val subtitle: String,
    val status: String // "Done", "Active", "Pending"
)

data class AiEvaluationProcessingUiState(
    val studentName: String = "Marcus Vance",
    val rollNo: String = "#24-A",
    val examName: String = "Physics Mid-Term",
    val progressPercent: Int = 71,
    val currentStep: Int = 5,
    val totalSteps: Int = 7,
    val estimatedSeconds: Int = 4,
    val stages: List<ProcessingStageItem> = listOf(
        ProcessingStageItem(1, "Stage 1: Uploading Scanned Pages", "2/2 pages cached", "Done"),
        ProcessingStageItem(2, "Stage 2: OCR & Handwriting Transcription", "Confidence: 98.2%", "Done"),
        ProcessingStageItem(3, "Stage 3: Matching Questions to Rubric", "3/3 questions mapped", "Done"),
        ProcessingStageItem(4, "Stage 4: Checking Answers Against Key", "Criteria verified", "Done"),
        ProcessingStageItem(5, "Stage 5: Calculating Question Marks", "Analyzing partial credit...", "Active"),
        ProcessingStageItem(6, "Stage 6: Generating Constructive Feedback", "Rubric-aligned comment synthesis", "Pending"),
        ProcessingStageItem(7, "Stage 7: Evaluation Report Ready", "Final score validation", "Pending")
    ),
    val isCompleted: Boolean = false
)

class AiEvaluationProcessingViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AiEvaluationProcessingUiState())
    val uiState: StateFlow<AiEvaluationProcessingUiState> = _uiState.asStateFlow()

    init {
        startSimulatedProgressTimer()
    }

    private fun startSimulatedProgressTimer() {
        viewModelScope.launch {
            delay(1500)
            _uiState.update { state ->
                val updatedStages = state.stages.map {
                    when (it.stageNumber) {
                        5 -> it.copy(status = "Done")
                        6 -> it.copy(status = "Active")
                        else -> it
                    }
                }
                state.copy(
                    progressPercent = 86,
                    currentStep = 6,
                    estimatedSeconds = 2,
                    stages = updatedStages
                )
            }

            delay(1500)
            _uiState.update { state ->
                val updatedStages = state.stages.map {
                    when (it.stageNumber) {
                        6 -> it.copy(status = "Done")
                        7 -> it.copy(status = "Done")
                        else -> it
                    }
                }
                state.copy(
                    progressPercent = 100,
                    currentStep = 7,
                    estimatedSeconds = 0,
                    stages = updatedStages,
                    isCompleted = true
                )
            }
        }
    }

    fun simulateInstantCompletion() {
        _uiState.update { state ->
            val updatedStages = state.stages.map { it.copy(status = "Done") }
            state.copy(
                progressPercent = 100,
                currentStep = 7,
                estimatedSeconds = 0,
                stages = updatedStages,
                isCompleted = true
            )
        }
    }
}
