package com.sauleiros.albumexplorer.infra.adapters.output.rest

import com.sauleiros.albumexplorer.application.ports.output.AlbumRepository
import com.sauleiros.albumexplorer.domain.model.Album
import com.sauleiros.albumexplorer.infra.adapters.output.rest.models.JSONPlaceholderAlbum
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
        return restClient.get().uri("/albums/{id}", id).retrieve().body(JSONPlaceholderAlbum::class.java)?.toDomain()
    }

    override fun findAll(): List<Album> {
        return restClient.get().uri(
            "/albums",
        ).retrieve().body(Array<JSONPlaceholderAlbum>::class.java)?.map { it.toDomain() } ?: emptyList()
    }
}
