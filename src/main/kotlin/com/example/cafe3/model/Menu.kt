package com.example.cafe3.model

import com.example.cafe3.dto.responseDto.MenuResponseDto
import jakarta.persistence.*

/**
 * 메뉴 엔티티 클래스
 * @property name 메뉴 이름
 * @property description 메뉴 설명
 * @property Price 메뉴 기본 가격
 * @property sales 메뉴 판매량
 * @property option 메뉴 옵션 리스트
 **/
@Entity
@Table(name = "MENU")
class Menu(

    @Column(name = "NAME", nullable = false)
    var name: String,

    @Column(name = "DESCRIPTION", nullable = false)
    var description: String,

    @Column(name = "PRICE", nullable = false)
    var price: Long,

    @Column(name = "SALES", nullable = false)
    var sales: Long = 0,

    @OneToMany(mappedBy = "menu")
    var option: MutableList<Option> = mutableListOf()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

@Entity
@Table(name = "OPTION")
class Option(
    @ManyToOne
    @JoinColumn(name = "MENU_ID")
    var menu: Menu
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

/**
 * Menu 엔티티를 MenuResponseDto로 변환하는 확장 함수
 * @return MenuResponseDto 변환된 DTO 객체
 */
fun Menu.toResponse() : MenuResponseDto {
    return MenuResponseDto(
        id!!,
        name,
        description,
        price,
        sales
    )
}
