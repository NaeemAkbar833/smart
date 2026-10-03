package com.example.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SmartPaperRepositoryImpl : SmartPaperRepository {
    override fun getFoundationStatus(): Flow<String> = flow {
        emit("SmartPaper AI Foundation Active")
    }
}
