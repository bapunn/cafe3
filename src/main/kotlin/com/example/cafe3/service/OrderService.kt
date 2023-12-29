package com.example.cafe3.service

import com.example.cafe3.dto.requestDto.OrderRequestDto
import com.example.cafe3.dto.responseDto.OrderResponseDto
import com.example.cafe3.enums.OrderStatusEnum
import com.example.cafe3.exception.CustomException
import com.example.cafe3.exception.ErrorCode
import com.example.cafe3.model.Order
import com.example.cafe3.model.OrderMenu
import com.example.cafe3.model.toResponse
import com.example.cafe3.repository.UserRepository
import com.example.cafe3.repository.MenuRepository
import com.example.cafe3.repository.OrderRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService(
    private val menuRepository: MenuRepository,
    private val userRepository: UserRepository,
    private val orderRepository: OrderRepository
) {
    @Transactional
    fun createOrder(orderRequest: OrderRequestDto): OrderResponseDto {

        val user = userRepository.findByIdOrNull(orderRequest.userId)
            ?: throw CustomException("user", ErrorCode.NOT_FOUND)

        val menuList = menuRepository.findMenuListById(orderRequest.menuList)
        if(menuList.size != orderRequest.menuList.size){
            throw CustomException("menu", ErrorCode.NOT_FOUND)
        }

        var totalAmount: Long = menuList.sumOf { it.price }

        if (user.balance < totalAmount) {
            throw CustomException("user", ErrorCode.BELOW_ZERO_AMOUNT)
        }

        user.balance -= totalAmount

        val newOrder = Order(
            user = user,
            totalAmount = totalAmount,
            status = OrderStatusEnum.COMPLETED
        )

        menuList.map {
            it.sales += 1
            newOrder.orderMenus.add(
                OrderMenu(
                    order = newOrder,
                    menu = it,
                    number = 1
                )
            )
        }

        return orderRepository.save(newOrder).toResponse()
    }

    @Transactional(readOnly = true)
    fun getOrderList() : List<OrderResponseDto> {
        val orderList = orderRepository.findAll()
        return orderList.map { it.toResponse() }
    }
}