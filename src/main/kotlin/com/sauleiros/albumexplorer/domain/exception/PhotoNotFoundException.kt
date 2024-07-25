package com.sauleiros.albumexplorer.domain.exception

class PhotoNotFoundException(id: Long) : Exception(
    "Photo with id $id not found!",
)
