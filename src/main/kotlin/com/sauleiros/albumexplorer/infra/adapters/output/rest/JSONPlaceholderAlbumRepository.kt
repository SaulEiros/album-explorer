package com.sauleiros.albumexplorer.infra.adapters.output.rest

import com.sauleiros.albumexplorer.application.ports.output.AlbumRepository
import com.sauleiros.albumexplorer.domain.model.Album
import com.sauleiros.albumexplorer.infra.adapters.output.rest.models.JSONPlaceholderAlbum
import com.sauleiros.albumexplorer.infra.adapters.output.rest.models.JSONPlaceholderPhoto
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestClient

@Repository
class JSONPlaceholderAlbumRepository(private val restClientBuilder: RestClient.Builder) : AlbumRepository {
    private lateinit var restClient: RestClient

    @Value("\${json-placeholder.url}")
    private lateinit var baseUrl: String

    @PostConstruct
    fun init() {
        restClient = restClientBuilder.baseUrl(baseUrl).build()
    }

    override fun findById(id: Long): Album? {
        val photos = getPhotosByAlbumId(id)
        return getAlbumById(id)?.apply {
            this.photos = photos.toList()
        }?.toDomain()
    }

    private fun getAlbumById(id: Long): JSONPlaceholderAlbum? {
        return try {
            restClient.get()
                .uri("/albums/{id}", id)
                .retrieve()
                .body(JSONPlaceholderAlbum::class.java)
        } catch (ex: Exception) {
            null
        }
    }

    private fun getPhotosByAlbumId(id: Long): List<JSONPlaceholderPhoto> {
        return restClient.get().uri(
            "/albums/{id}/photos",
            id,
        ).retrieve().body(Array<JSONPlaceholderPhoto>::class.java)?.toList() ?: emptyList()
    }

    override fun findAll(): List<Album> {
        val albums = getAllAlbums()
        val photos = getAllPhotos()

        return albums.map { album ->
            val albumPhotos = photos.filter { it.albumId == album.id }
            album.photos = albumPhotos
            album.toDomain()
        }
    }

    private fun getAllAlbums(): List<JSONPlaceholderAlbum> {
        return restClient.get().uri(
            "/albums",
        ).retrieve().body(Array<JSONPlaceholderAlbum>::class.java)?.toList() ?: emptyList()
    }

    private fun getAllPhotos(): List<JSONPlaceholderPhoto> {
        return restClient.get().uri(
            "/photos",
        ).retrieve().body(Array<JSONPlaceholderPhoto>::class.java)?.toList() ?: emptyList()
    }
}
