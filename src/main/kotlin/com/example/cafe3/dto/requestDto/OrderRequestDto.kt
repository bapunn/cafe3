package com.example.cafe3.dto.requestDto

data class OrderRequestDto(
    var userId: Long,
    var menuList: List<Long>
) {

}
