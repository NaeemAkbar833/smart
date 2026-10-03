package com.example.data.repository

import kotlinx.coroutines.flow.Flow

interface SmartPaperRepository {
    fun getFoundationStatus(): Flow<String>
}
