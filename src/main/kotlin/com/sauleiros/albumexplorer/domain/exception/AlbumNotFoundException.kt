package com.sauleiros.albumexplorer.domain.exception

class AlbumNotFoundException(id: Long) : Exception(
    "Album with id: $id not found!",
)
