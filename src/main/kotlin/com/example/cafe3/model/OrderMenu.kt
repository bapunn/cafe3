package com.example.cafe3.model

import jakarta.persistence.*

/**
 * 주문에 포함된 메뉴를 나타내는 엔티티 클래스
 * @property order 주문 정보
 * @property menu 주문된 메뉴 정보
 * @property number 주문된 메뉴의 수량
 */
@Entity
@Table(name = "ORDER_MENU")
class OrderMenu(
    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "ORDER_ID")
    var order: Order,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "MENU_ID")
    var menu: Menu,

    @Column(name = "NUMBER")
    var number: Long
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
}
