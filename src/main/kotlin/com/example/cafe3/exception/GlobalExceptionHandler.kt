package com.example.cafe3.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.http.HttpStatus

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(CustomException::class)
    fun handleCustomException(exception: CustomException): ResponseEntity<String> {
        return ResponseEntity
            .status(exception.errorCode.httpStatus)
            .body(exception.field + exception.errorCode.message)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(exception: MethodArgumentNotValidException): ResponseEntity<String> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST.value())
            .body(exception.fieldErrors.map {
                it.field + it.defaultMessage
            }.toString())
    }
}