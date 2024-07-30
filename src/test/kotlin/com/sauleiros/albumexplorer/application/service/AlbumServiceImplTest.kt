package com.sauleiros.albumexplorer.application.service

import com.sauleiros.albumexplorer.application.ports.output.AlbumRepository
import com.sauleiros.albumexplorer.domain.exception.AlbumNotFoundException
import com.sauleiros.albumexplorer.domain.model.Album
import com.sauleiros.albumexplorer.domain.model.Photo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@ExtendWith(MockitoExtension::class)
class AlbumServiceImplTest {
    @Mock
    private lateinit var albumRepository: AlbumRepository

    @InjectMocks
    private lateinit var albumService: AlbumServiceImpl

    private lateinit var album: Album

    @BeforeEach
    fun setUp() {
        val photos = listOf(Photo(id = 1L, albumId = 1L, title = "Test Photo", url = "Test Url", thumbnailUrl = "Test Thumbnail"))
        album = Album(id = 1L, userId = 1L, title = "Test Album", photos = photos)
    }

    @Test
    fun `GIVEN existing id WHEN album is requested THEN the album is returned `() {
        // GIVEN
        Mockito.`when`(albumRepository.findById(1L)).thenReturn(album)

        // WHEN
        val result = albumService.findById(1L)

        // THEN
        assertNotNull(result)
        assertEquals(album, result)
        Mockito.verify(albumRepository, Mockito.times(1)).findById(1L)
    }

    @Test
    fun `GIVEN non existing id WHEN album is requested THEN AlbumNotFoundException is thrown `() {
        // GIVEN
        Mockito.`when`(albumRepository.findById(1L)).thenReturn(null)

        // WHEN & THEN
        assertThrows<AlbumNotFoundException> {
            albumService.findById(1L)
        }

        Mockito.verify(albumRepository, Mockito.times(1)).findById(1L)
    }

    @Test
    fun `WHEN all albums are requested THEN an album list is returned `() {
        // GIVEN
        val albums = listOf(album)
        Mockito.`when`(albumRepository.findAll()).thenReturn(albums)

        // WHEN
        val result = albumService.findAll()

        // THEN
        assertNotNull(result)
        assertEquals(albums.size, result.size)
        assertEquals(albums, result)
        Mockito.verify(albumRepository, Mockito.times(1)).findAll()
    }
}
