package com.sauleiros.albumexplorer.domain.model

data class Photo(
    val id: Long,
    val albumId: Long,
    val title: String,
    val url: String,
    val thumbnailUrl: String,
)
