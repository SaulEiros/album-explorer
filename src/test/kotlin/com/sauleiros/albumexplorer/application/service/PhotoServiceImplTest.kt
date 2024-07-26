package com.sauleiros.albumexplorer.application.service

import com.sauleiros.albumexplorer.application.ports.output.PhotoRepository
import com.sauleiros.albumexplorer.domain.exception.PhotoNotFoundException
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
import kotlin.test.assertTrue

@ExtendWith(MockitoExtension::class)
class PhotoServiceImplTest {
    @Mock
    private lateinit var photoRepository: PhotoRepository

    @InjectMocks
    private lateinit var photoService: PhotoServiceImpl

    private lateinit var photo: Photo

    @BeforeEach
    fun setUp() {
        photo = Photo(id = 1L, albumId = 1L, title = "Test Photo", url = "Test Url", thumbnailUrl = "Test Thumbnail")
    }

    @Test
    fun `GIVEN existing id WHEN photo is requested THEN the photo is returned `() {
        // GIVEN
        Mockito.`when`(photoRepository.findById(1L)).thenReturn(photo)

        // WHEN
        val result = photoService.findById(1L)

        // THEN
        assertNotNull(result)
        assertEquals(photo, result)
        Mockito.verify(photoRepository, Mockito.times(1)).findById(1L)
    }

    @Test
    fun `GIVEN non existing id WHEN photo is requested THEN PhotoNotFoundException is thrown `() {
        // GIVEN
        Mockito.`when`(photoRepository.findById(1L)).thenReturn(null)

        // WHEN & THEN
        assertThrows<PhotoNotFoundException> {
            photoService.findById(1L)
        }

        Mockito.verify(photoRepository, Mockito.times(1)).findById(1L)
    }

    @Test
    fun `WHEN all photos are requested THEN a photo list is returned `() {
        // GIVEN
        val albums = listOf(photo)
        Mockito.`when`(photoRepository.findAll()).thenReturn(albums)

        // WHEN
        val result = photoService.findAll()

        // THEN
        assertNotNull(result)
        assertEquals(albums.size, result.size)
        assertEquals(albums, result)
        Mockito.verify(photoRepository, Mockito.times(1)).findAll()
    }

    @Test
    fun `GIVEN an albumId with photos WHEN photos by albumId are requested THEN a photo list is returned`() {
        // GIVEN
        val photos = listOf(photo)
        Mockito.`when`(photoRepository.findByAlbumId(1L)).thenReturn(photos)

        // WHEN
        val result = photoService.findByAlbumId(1L)

        // THEN
        assertNotNull(result)
        assertEquals(1, result.size)
        assertEquals(photos, result)
        Mockito.verify(photoRepository, Mockito.times(1)).findByAlbumId(1L)
    }

    @Test
    fun `GIVEN an albumId without photos WHEN photos by albumId are requested THEN an empty list is returned`() {
        // GIVEN
        Mockito.`when`(photoRepository.findByAlbumId(1L)).thenReturn(emptyList())

        // WHEN
        val result = photoService.findByAlbumId(1L)

        // THEN
        assertNotNull(result)
        assertTrue(result.isEmpty())
        Mockito.verify(photoRepository, Mockito.times(1)).findByAlbumId(1L)
    }
}
