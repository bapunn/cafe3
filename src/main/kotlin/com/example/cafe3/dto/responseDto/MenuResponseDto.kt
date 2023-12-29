package com.example.cafe3.dto.responseDto

data class MenuResponseDto(
    var menuId: Long,
    var name: String,
    var description: String,
    var price: Long,
    var sales: Long
)
