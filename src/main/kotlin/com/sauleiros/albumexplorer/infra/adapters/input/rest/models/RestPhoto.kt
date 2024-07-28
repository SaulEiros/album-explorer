package com.sauleiros.albumexplorer.infra.adapters.input.rest.models

import com.sauleiros.albumexplorer.domain.model.Photo
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Model for details of a Photo.")
data class RestPhoto(
    @field:Schema(
        description = "The Id of the Photo",
        example = "1",
        type = "long",
    )
    val id: Long,
    @field:Schema(
        description = "The Album Id to which the Photo belongs",
        example = "1",
        type = "long",
    )
    val albumId: Long,
    @field:Schema(
        description = "The title of the Photo",
        example = "Photo Title",
        type = "String",
    )
    val title: String,
    @field:Schema(
        description = "The url to retrieve the actual Photo",
        example = "https://example.com",
        type = "String",
    )
    val url: String,
    @field:Schema(
        description = "The url to retrieve the thumbnail of the Photo",
        example = "https://example.com",
        type = "String",
    )
    val thumbnailUrl: String,
) {
    companion object {
        fun fromDomain(photo: Photo) =
            RestPhoto(
                id = photo.id,
                albumId = photo.albumId,
                title = photo.title,
                url = photo.url,
                thumbnailUrl = photo.thumbnailUrl,
            )
    }
}
