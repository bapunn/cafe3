package com.example.cafe3.controller

import com.example.cafe3.dto.requestDto.ChargeRequestDto
import com.example.cafe3.dto.responseDto.ChargeResponseDto
import com.example.cafe3.dto.requestDto.UserRequestDto
import com.example.cafe3.dto.responseDto.UserResponseDto
import com.example.cafe3.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

/**
 * 사용자와 관련된 API를 처리하는 컨트롤러 클래스
 */
@RestController
@RequestMapping("/api")
class UserController(
    val userService: UserService
) {

    /**
     * 사용자 생성 엔드포인트
     * @param userRequest 생성할 사용자의 요청 DTO
     * @return 생성된 사용자의 응답 DTO
     */
    @PostMapping("/users")
    fun createUser(
        @RequestBody userRequest: UserRequestDto
    ): ResponseEntity<UserResponseDto> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userService.createUser(userRequest))
    }

    /**
     * 포인트 충전 엔드포인트
     * @param chargeRequest 충전할 포인트의 요청 DTO
     * @param memberId 충전할 사용자의 ID
     * @return 충전된 포인트의 응답 DTO
     */
    @PutMapping("/charges/{memberId}")
    fun chargePoint(
        @RequestBody @Validated chargeRequest: ChargeRequestDto,
        @PathVariable memberId: Long
    ): ResponseEntity<ChargeResponseDto> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userService.addBalance(chargeRequest, memberId))
    }
}
