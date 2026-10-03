package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.models.SmartPaperUiState
import com.example.data.repository.SmartPaperRepository
import com.example.di.AppContainer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: SmartPaperRepository = AppContainer.repository
) : ViewModel() {

    private val _uiState = MutableStateFlow<SmartPaperUiState>(SmartPaperUiState.Initial)
    val uiState: StateFlow<SmartPaperUiState> = _uiState.asStateFlow()

    init {
        loadFoundationStatus()
    }

    fun loadFoundationStatus() {
        viewModelScope.launch {
            _uiState.value = SmartPaperUiState.Loading
            repository.getFoundationStatus().collect { status ->
                _uiState.value = SmartPaperUiState.Success(
                    appName = "SmartPaper AI",
                    architectureStatus = status
                )
            }
        }
    }
}
