package com.example.ui.scan

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ScanCameraUiState(
    val studentName: String = "Marcus Vance",
    val studentRoll: String = "#24-A",
    val isFlashOn: Boolean = false,
    val capturedPagesCount: Int = 1,
    val alignmentMessage: String = "Align paper within frame"
)

class ScanCameraViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ScanCameraUiState())
    val uiState: StateFlow<ScanCameraUiState> = _uiState.asStateFlow()

    fun toggleFlash() {
        _uiState.update { it.copy(isFlashOn = !it.isFlashOn) }
    }

    fun capturePage() {
        _uiState.update { it.copy(capturedPagesCount = it.capturedPagesCount + 1) }
    }
}
