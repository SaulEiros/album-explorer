package com.sauleiros.albumexplorer.infra.adapters.output.rest

import com.sauleiros.albumexplorer.application.ports.output.PhotoRepository
import com.sauleiros.albumexplorer.domain.model.Photo
import com.sauleiros.albumexplorer.infra.adapters.output.rest.` models`.JSONPlaceholderPhoto
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestClient

@Repository
class JSONPlaceholderPhotoRepository(private val restClientBuilder: RestClient.Builder) : PhotoRepository {
    private lateinit var restClient: RestClient

    @Value("\${json-placeholder.url}")
    private lateinit var baseUrl: String

    @PostConstruct
    fun init() {
        restClient = restClientBuilder.baseUrl(baseUrl).build()
    }

    override fun findById(id: Long): Photo? {
        return restClient.get().uri("/photos/{id}", id).retrieve().body(JSONPlaceholderPhoto::class.java)?.toDomain()
    }

    override fun findAll(): List<Photo> {
        return restClient.get().uri(
            "/photos",
        ).retrieve().body(Array<JSONPlaceholderPhoto>::class.java)?.map { it.toDomain() } ?: emptyList()
    }

    override fun findByAlbumId(albumId: Long): List<Photo> {
        return restClient.get().uri(
            "/photos?albumId={albumId}",
            albumId,
        ).retrieve().body(Array<JSONPlaceholderPhoto>::class.java)?.map { it.toDomain() } ?: emptyList()
    }
}
