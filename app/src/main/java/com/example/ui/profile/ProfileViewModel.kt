package com.example.ui.profile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ProfileUiState(
    val teacherName: String = "Dr. Jane Smith",
    val teacherTitle: String = "High School Physics Educator • Senior Faculty",
    val teacherId: String = "ID: FAC-2024-8841",
    val activeCohorts: Int = 6,
    val configuredExams: Int = 18,
    val papersGraded: Int = 412,
    val gradingScalePreset: String = "Standard 4-point AP & IB Weighted Scale",
    val ocrSensitivity: String = "High Accuracy (Equations & Cursive...)",
    val defaultBaseline: String = "Standard 100 pt total • Passing at 65%",
    val backendCloudSync: String = "Connected to Supabase Cloud • Synced 2m ago",
    val isCloudSynced: Boolean = true
)

class ProfileViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun toggleCloudSync() {
        _uiState.update { it.copy(isCloudSynced = !it.isCloudSynced) }
    }
}
