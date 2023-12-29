package com.example.cafe3.controller

import com.example.cafe3.dto.requestDto.MenuRequestDto
import com.example.cafe3.dto.responseDto.MenuResponseDto
import com.example.cafe3.service.MenuService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * 메뉴 관련 API를 처리하는 컨트롤러 클래스
 */
@RestController
@RequestMapping("/api")
class MenuController(
    private val menuService: MenuService
) {

    /**
     * 모든 메뉴 리스트를 조회하는 엔드포인트
     * @return 모든 메뉴의 응답 DTO 리스트
     */
    @GetMapping("/menus")
    fun getMenuList(): ResponseEntity<List<MenuResponseDto>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(menuService.getMenuList())
    }

    /**
     * 메뉴를 추가하는 엔드포인트
     * @param menuRequest 추가할 메뉴의 요청 DTO
     * @return 추가된 메뉴의 응답 DTO
     */
    @PostMapping("/menus")
    fun addMenu(
        @RequestBody menuRequest: MenuRequestDto
    ): ResponseEntity<MenuResponseDto> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(menuService.createMenu(menuRequest))
    }
}
