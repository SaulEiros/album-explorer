package com.sauleiros.albumexplorer.application.service

import com.sauleiros.albumexplorer.application.ports.input.AlbumService
import com.sauleiros.albumexplorer.application.ports.output.AlbumRepository
import com.sauleiros.albumexplorer.domain.model.Album
import org.springframework.stereotype.Service

@Service
class AlbumServiceImpl(private val repository: AlbumRepository) : AlbumService {
    override fun findById(id: Long): Album {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Album> {
        TODO("Not yet implemented")
    }
}
