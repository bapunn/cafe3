package com.example.cafe3.service

import com.example.cafe3.dto.requestDto.MenuRequestDto
import com.example.cafe3.dto.responseDto.MenuResponseDto
import com.example.cafe3.model.Menu
import com.example.cafe3.model.toResponse
import com.example.cafe3.repository.MenuRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
@Service
class MenuService(
    private val menuRepository: MenuRepository
) {
    @Transactional(readOnly = true)
    fun getMenuList(): List<MenuResponseDto>? {
        return menuRepository.findAll().map { it.toResponse() }
    }

    @Transactional
    fun createMenu(menuRequest: MenuRequestDto): MenuResponseDto? {
        val menu = Menu(
            name = menuRequest.name,
            description = menuRequest.description,
            price = menuRequest.price
        )
        return menuRepository.save(menu).toResponse()
    }

}
