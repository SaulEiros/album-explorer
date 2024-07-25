package com.sauleiros.albumexplorer.application.ports.output

import com.sauleiros.albumexplorer.domain.model.Photo

interface PhotoRepository {
    fun findById(id: Long): Photo?

    fun findAll(): List<Photo>

    fun findByAlbumId(albumI: Long): List<Photo>
}
