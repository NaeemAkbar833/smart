package com.example.ui.students

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class Student(
    val id: Int,
    val rollNo: String,
    val name: String,
    val status: String = "Ready for Scan"
)

data class StudentListUiState(
    val searchQuery: String = "",
    val selectedFilterTab: Int = 0,
    val students: List<Student> = emptyList(),
    val isAddDialogOpen: Boolean = false,
    val newStudentName: String = "",
    val newStudentRollNo: String = ""
)

class StudentListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(StudentListUiState())
    val uiState: StateFlow<StudentListUiState> = _uiState.asStateFlow()

    private var nextId = 1

    fun onSearchQueryChange(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun onSelectFilterTab(tabIndex: Int) {
        _uiState.update { it.copy(selectedFilterTab = tabIndex) }
    }

    fun openAddDialog() {
        _uiState.update { it.copy(isAddDialogOpen = true) }
    }

    fun closeAddDialog() {
        _uiState.update {
            it.copy(
                isAddDialogOpen = false,
                newStudentName = "",
                newStudentRollNo = ""
            )
        }
    }

    fun onNewStudentNameChange(name: String) {
        _uiState.update { it.copy(newStudentName = name) }
    }

    fun onNewStudentRollNoChange(rollNo: String) {
        _uiState.update { it.copy(newStudentRollNo = rollNo) }
    }

    fun addStudent() {
        _uiState.update { state ->
            if (state.newStudentName.isBlank()) return@update state
            val roll = if (state.newStudentRollNo.isBlank()) "#${String.format("%02d", state.students.size + 1)}" else state.newStudentRollNo
            val newStudent = Student(
                id = nextId++,
                rollNo = roll,
                name = state.newStudentName.trim(),
                status = "Ready for Scan"
            )
            state.copy(
                students = state.students + newStudent,
                isAddDialogOpen = false,
                newStudentName = "",
                newStudentRollNo = ""
            )
        }
    }

    fun deleteStudent(id: Int) {
        _uiState.update { state ->
            state.copy(students = state.students.filter { it.id != id })
        }
    }
}
