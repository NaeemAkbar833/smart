package com.example.ui.scan

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ScannedPage(
    val id: Int,
    val pageNumber: Int,
    val qualityTag: String = "1080p • Clear",
    val rotationDegrees: Int = 0,
    val paperFormat: String = "A4 Matched"
)

data class ReviewScanUiState(
    val studentName: String = "Marcus Vance",
    val rollNo: String = "#24-A",
    val examName: String = "Mid-Term Exam",
    val pages: List<ScannedPage> = listOf(
        ScannedPage(id = 1, pageNumber = 1),
        ScannedPage(id = 2, pageNumber = 2)
    )
)

class ReviewScanViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ReviewScanUiState())
    val uiState: StateFlow<ReviewScanUiState> = _uiState.asStateFlow()

    private var nextPageId = 3

    fun rotatePage(id: Int) {
        _uiState.update { state ->
            val updated = state.pages.map { page ->
                if (page.id == id) {
                    page.copy(rotationDegrees = (page.rotationDegrees + 90) % 360)
                } else page
            }
            state.copy(pages = updated)
        }
    }

    fun deletePage(id: Int) {
        _uiState.update { state ->
            val updated = state.pages.filter { it.id != id }
                .mapIndexed { index, page -> page.copy(pageNumber = index + 1) }
            state.copy(pages = updated)
        }
    }

    fun addPage() {
        _uiState.update { state ->
            val newPage = ScannedPage(
                id = nextPageId++,
                pageNumber = state.pages.size + 1
            )
            state.copy(pages = state.pages + newPage)
        }
    }

    fun retakeAll() {
        _uiState.update { state ->
            state.copy(
                pages = listOf(
                    ScannedPage(id = nextPageId++, pageNumber = 1)
                )
            )
        }
    }
}
