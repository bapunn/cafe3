package com.example.cafe3.exception

class CustomException(
    val field: String,
    val errorCode: ErrorCode
): RuntimeException() {
}