package com.sauleiros.albumexplorer.application.ports.input

import com.sauleiros.albumexplorer.domain.model.Photo

interface PhotoService {
    fun findById(id: Long): Photo

    fun findAll(): List<Photo>

    fun findByAlbumId(albumId: Long): List<Photo>
}
