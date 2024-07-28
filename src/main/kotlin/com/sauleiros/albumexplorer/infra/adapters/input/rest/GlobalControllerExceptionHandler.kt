package com.sauleiros.albumexplorer.infra.adapters.input.rest

import com.sauleiros.albumexplorer.domain.exception.PhotoNotFoundException
import com.sauleiros.albumexplorer.infra.adapters.input.rest.models.ErrorMessageModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@ControllerAdvice
class GlobalControllerExceptionHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handlePhotoNotFount(ex: PhotoNotFoundException): ResponseEntity<ErrorMessageModel> {
        val errorMessage =
            ErrorMessageModel(
                status = HttpStatus.NOT_FOUND.value(),
                message = ex.message,
            )

        return ResponseEntity(errorMessage, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleTypeMismatchParameterException(ex: MethodArgumentTypeMismatchException): ResponseEntity<ErrorMessageModel> {
        val errorMessage =
            ErrorMessageModel(
                status = HttpStatus.BAD_REQUEST.value(),
                message =
                    "The ${ex.parameter.parameterName} parameter is not of the correct type:" +
                        " ${ex.parameter.parameterType.simpleName}",
            )

        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleRuntimeException(ex: RuntimeException): ResponseEntity<ErrorMessageModel> {
        val errorMessage =
            ErrorMessageModel(
                status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message = ex.message,
            )

        return ResponseEntity(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
