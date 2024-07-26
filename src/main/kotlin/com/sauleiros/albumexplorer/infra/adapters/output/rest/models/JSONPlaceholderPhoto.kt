package com.sauleiros.albumexplorer.infra.adapters.output.rest.models

import com.sauleiros.albumexplorer.domain.model.Photo

data class JSONPlaceholderPhoto(
    val albumId: Long,
    val id: Long,
    val title: String,
    val url: String,
    val thumbnailUrl: String,
) {
    fun toDomain(): Photo =
        Photo(
            id = id,
            albumId = albumId,
            title = title,
            url = url,
            thumbnailUrl = thumbnailUrl,
        )
}
