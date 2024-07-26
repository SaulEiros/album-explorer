package com.sauleiros.albumexplorer.infra.adapters.output.rest.models

import com.sauleiros.albumexplorer.domain.model.Album

data class JSONPlaceholderAlbum(
    val id: Long,
    val userId: Long,
    val title: String,
) {
    fun toDomain(): Album =
        Album(
            id = id,
            userId = userId,
            title = title,
        )
}
