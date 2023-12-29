package com.example.cafe3.dto.requestDto

import jakarta.validation.constraints.Min

data class ChargeRequestDto(
    @field:Min(value = 1000, message = "최소 충전금액은 천원부터입니다.")
    var chargeAmount: Long
) {
}