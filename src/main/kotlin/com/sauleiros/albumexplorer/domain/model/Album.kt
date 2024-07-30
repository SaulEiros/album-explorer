package com.sauleiros.albumexplorer.domain.model

data class Album(
    val id: Long,
    val userId: Long,
    val title: String,
    val photos: List<Photo>,
)
