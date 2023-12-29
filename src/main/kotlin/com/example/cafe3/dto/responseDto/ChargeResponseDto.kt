package com.example.cafe3.dto.responseDto

data class ChargeResponseDto(
    var userId: Long,
    var userName: String,
    var chargeAmount: Long,
    var balance: Long
) {
}