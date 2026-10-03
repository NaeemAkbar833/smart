package com.example.ui.scan

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ScanPaperUiState(
    val studentName: String = "Aarav Sharma",
    val rollNo: String = "1024",
    val className: String = "Class 10-A",
    val subject: String = "Mathematics",
    val assessment: String = "Mid-Term Exam",
    val isScanSimulated: Boolean = false,
    val scannedPageCount: Int = 0
)

class ScanPaperViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ScanPaperUiState())
    val uiState: StateFlow<ScanPaperUiState> = _uiState.asStateFlow()

    fun simulateCameraScan() {
        _uiState.update {
            it.copy(
                isScanSimulated = true,
                scannedPageCount = it.scannedPageCount + 1
            )
        }
    }

    fun resetScan() {
        _uiState.update {
            it.copy(
                isScanSimulated = false,
                scannedPageCount = 0
            )
        }
    }
}
