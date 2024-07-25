package com.sauleiros.albumexplorer.infra.adapters.output.rest

import com.sauleiros.albumexplorer.application.ports.output.PhotoRepository
import com.sauleiros.albumexplorer.domain.model.Photo
import org.springframework.stereotype.Repository

@Repository
class JSONPlaceholderPhotoRepository : PhotoRepository {
    override fun findById(id: Long): Photo? {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Photo> {
        TODO("Not yet implemented")
    }

    override fun findByAlbumId(albumI: Long): List<Photo> {
        TODO("Not yet implemented")
    }
}
