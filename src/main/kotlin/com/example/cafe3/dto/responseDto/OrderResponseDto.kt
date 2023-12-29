package com.example.cafe3.dto.responseDto

data class OrderResponseDto(
    var orderId: Long,
    var orderStatus: String,
    var totalAmount: Long,
    var userId: Long,
    var menuList: List<MenuResponseDto>
) {


}
