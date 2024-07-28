package com.sauleiros.albumexplorer.infra.adapters.input.rest

import com.sauleiros.albumexplorer.application.ports.input.AlbumService
import com.sauleiros.albumexplorer.infra.adapters.input.rest.models.RestAlbum
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "Albums")
@RequestMapping("/albums")
class AlbumController(private val albumService: AlbumService) {
    @Operation(summary = "Get an Album by its id")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "The requested Album",
                content = [
                    Content(mediaType = "application/json", schema = Schema(implementation = RestAlbum::class)),
                ],
            ),
            ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
            ApiResponse(responseCode = "404", description = "Photo not found", content = [Content()]),
        ],
    )
    @GetMapping("/{id}")
    fun getById(
        @Parameter(description = "Id of the Album to be searched") @PathVariable id: Long,
    ): RestAlbum {
        val result = albumService.findById(id)
        return RestAlbum.fromDomain(result)
    }

    @Operation(summary = "Gets all existing album")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description =
                    "The list of Albums.",
                content = [
                    Content(mediaType = "application/json", schema = Schema(implementation = RestAlbum::class)),
                ],
            ),
            ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
            ApiResponse(responseCode = "404", description = "Photo not found", content = [Content()]),
        ],
    )
    @GetMapping
    fun findAlbums(): List<RestAlbum> {
        val result = albumService.findAll()
        return result.map { RestAlbum.fromDomain(it) }
    }
}
