package com.sauleiros.albumexplorer.infra.adapters.input.rest.models

import com.sauleiros.albumexplorer.domain.model.Album
import io.swagger.v3.oas.annotations.media.Schema

data class RestAlbum(
    @field:Schema(
        description = "The Id of the Album",
        example = "1",
        type = "long",
    )
    val id: Long,
    @field:Schema(
        description = "The User Id that owns the Album",
        example = "1",
        type = "long",
    )
    val userId: Long,
    @field:Schema(
        description = "The title of the Album",
        example = "Album Title",
        type = "String",
    )
    val title: String,
) {
    companion object {
        fun fromDomain(album: Album): RestAlbum =
            RestAlbum(
                id = album.id,
                userId = album.userId,
                title = album.title,
            )
    }
}
