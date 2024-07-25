package com.sauleiros.albumexplorer.infra.adapters.output.rest

import com.sauleiros.albumexplorer.application.ports.output.AlbumRepository
import com.sauleiros.albumexplorer.domain.model.Album
import org.springframework.stereotype.Repository

@Repository
class JSONPlaceholderAlbumRepository : AlbumRepository {
    override fun findById(id: Long): Album? {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Album> {
        TODO("Not yet implemented")
    }
}
