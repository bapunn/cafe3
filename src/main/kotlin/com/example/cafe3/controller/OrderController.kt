package com.example.cafe3.controller

import com.example.cafe3.dto.requestDto.OrderRequestDto
import com.example.cafe3.dto.responseDto.OrderResponseDto
import com.example.cafe3.service.OrderService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * 주문과 관련된 API를 처리하는 컨트롤러 클래스
 */
@RestController
@RequestMapping("/api")
class OrderController(
    private val orderService: OrderService,
) {

    /**
     * 주문 생성 엔드포인트
     * @param orderRequest 생성할 주문의 요청 DTO
     * @return 생성된 주문의 응답 DTO
     */
    @PostMapping("/orders")
    fun createOrder(@RequestBody orderRequest: OrderRequestDto): ResponseEntity<OrderResponseDto> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(orderService.createOrder(orderRequest))
    }

    /**
     * 모든 주문 리스트 조회 엔드포인트
     * @return 모든 주문의 응답 DTO 리스트
     */
    @GetMapping("/orders")
    fun getOrderList(): ResponseEntity<List<OrderResponseDto>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(orderService.getOrderList())
    }
}
