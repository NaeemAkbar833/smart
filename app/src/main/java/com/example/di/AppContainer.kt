package com.example.di

import com.example.data.repository.SmartPaperRepository
import com.example.data.repository.SmartPaperRepositoryImpl

/**
 * Dependency Injection Container for manual DI.
 */
object AppContainer {
    val repository: SmartPaperRepository by lazy {
        SmartPaperRepositoryImpl()
    }
}
