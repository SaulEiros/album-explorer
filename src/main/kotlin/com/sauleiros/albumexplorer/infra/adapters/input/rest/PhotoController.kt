package com.sauleiros.albumexplorer.infra.adapters.input.rest

import com.sauleiros.albumexplorer.application.ports.input.PhotoService
import com.sauleiros.albumexplorer.infra.adapters.input.rest.models.RestPhoto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Photos")
@RequestMapping("/photos")
class PhotoController(private val photoService: PhotoService) {
    @Operation(summary = "Get a Photo by its id")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Found the Photo",
                content = [
                    Content(mediaType = "application/json", schema = Schema(implementation = RestPhoto::class)),
                ],
            ),
            ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
            ApiResponse(responseCode = "404", description = "Photo not found", content = [Content()]),
        ],
    )
    @GetMapping("/{id}")
    fun getById(
        @Parameter(description = "Id of the Photo to be searched") @PathVariable id: Long,
    ): RestPhoto {
        val result = photoService.findById(id)
        return RestPhoto.fromDomain(result)
    }

    @Operation(summary = "Gets all existing photos. If albumId is provided, it will return photos that belong to that album.")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description =
                    "The list of Photos. If albumId is provided, and it does not exists or has no photos, " +
                        "an empty list will be returned.",
                content = [
                    Content(mediaType = "application/json", schema = Schema(implementation = RestPhoto::class)),
                ],
            ),
            ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
            ApiResponse(responseCode = "404", description = "Photo not found", content = [Content()]),
        ],
    )
    @GetMapping
    fun getByAlbumId(
        @Parameter(description = "Id of the Album which the Photos belongs") @RequestParam albumId: Long?,
    ): List<RestPhoto> {
        val result = photoService.find(albumId)
        return result.map { RestPhoto.fromDomain(it) }
    }
}
