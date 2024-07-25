package com.sauleiros.albumexplorer.application.ports.output

import com.sauleiros.albumexplorer.domain.model.Album

interface AlbumRepository {
    fun findById(id: Long): Album?

    fun findAll(): List<Album>
}
