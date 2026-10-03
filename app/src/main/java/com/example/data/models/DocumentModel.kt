package com.example.data.models

data class DocumentModel(
    val id: String,
    val title: String,
    val createdAt: Long = System.currentTimeMillis(),
    val status: String = "Ready"
)
