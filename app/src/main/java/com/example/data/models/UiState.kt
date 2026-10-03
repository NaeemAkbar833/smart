package com.example.data.models

sealed interface SmartPaperUiState {
    object Initial : SmartPaperUiState
    object Loading : SmartPaperUiState
    data class Success(
        val appName: String,
        val architectureStatus: String,
        val timestamp: Long = System.currentTimeMillis()
    ) : SmartPaperUiState
    data class Error(val message: String) : SmartPaperUiState
}
