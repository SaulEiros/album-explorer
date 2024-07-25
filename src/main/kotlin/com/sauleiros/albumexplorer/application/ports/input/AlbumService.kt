package com.sauleiros.albumexplorer.application.ports.input

import com.sauleiros.albumexplorer.domain.model.Album

interface AlbumService {
    fun findById(id: Long): Album

    fun findAll(): List<Album>
}
