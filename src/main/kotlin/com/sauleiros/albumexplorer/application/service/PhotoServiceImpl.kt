package com.sauleiros.albumexplorer.application.service

import com.sauleiros.albumexplorer.application.ports.input.PhotoService
import com.sauleiros.albumexplorer.application.ports.output.PhotoRepository
import com.sauleiros.albumexplorer.domain.model.Photo
import org.springframework.stereotype.Service

@Service
class PhotoServiceImpl(private val repository: PhotoRepository) : PhotoService {
    override fun findById(id: Long): Photo {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Photo> {
        TODO("Not yet implemented")
    }

    override fun findByAlbumId(albumId: Long): List<Photo> {
        TODO("Not yet implemented")
    }
}
