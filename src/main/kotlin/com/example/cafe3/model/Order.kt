package com.example.cafe3.model

import com.example.cafe3.dto.responseDto.OrderResponseDto
import com.example.cafe3.enums.OrderStatusEnum
import jakarta.persistence.*

/**
 * 주문 엔티티 클래스
 * @property user 주문한 사용자
 * @property orderMenus 주문된 메뉴 목록
 * @property totalAmount 주문 총액
 * @property status 주문 상태
 */
@Entity
@Table(name = "ORDERS")
class Order(
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    var user: User,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    var orderMenus: MutableSet<OrderMenu> = mutableSetOf(),

    @Column(name = "TOTAL_AMOUNT")
    var totalAmount: Long,

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    var status: OrderStatusEnum
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

/**
 * Order 엔티티를 OrderResponseDto로 변환하는 확장 함수
 * @return OrderResponseDto 변환된 DTO 객체
 */
fun Order.toResponse(): OrderResponseDto {
    return OrderResponseDto(
        orderId = id!!,
        totalAmount = totalAmount,
        orderStatus = status.toString(),
        userId = user.id!!,
        menuList = orderMenus.map { it.menu.toResponse() }
    )
}
