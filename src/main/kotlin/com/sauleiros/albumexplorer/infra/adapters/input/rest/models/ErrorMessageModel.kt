package com.sauleiros.albumexplorer.infra.adapters.input.rest.models

data class ErrorMessageModel(
    var status: Int? = null,
    var message: String? = null,
)
