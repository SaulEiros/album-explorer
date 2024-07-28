package com.sauleiros.albumexplorer.application.ports.input

import com.sauleiros.albumexplorer.domain.model.Photo

interface PhotoService {
    fun findById(id: Long): Photo

    fun find(albumId: Long?): List<Photo>
}
