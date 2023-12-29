package com.example.cafe3.model

import com.example.cafe3.dto.responseDto.UserResponseDto
import com.example.cafe3.enums.UserRoleEnum
import jakarta.persistence.*

/**
 * 사용자 정보를 나타내는 엔티티 클래스
 * @property name 사용자 이름
 * @property password 사용자 비밀번호
 * @property balance 사용자의 잔액
 * @property role 사용자 역할 (권한)
 */
@Entity
@Table(name = "User")
class User(
    @Column(name = "NAME")
    var name: String,

    @Column(name = "PASSWORD")
    var password: String,

    @Column(name = "BALANCE")
    var balance: Long = 0,

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    var role: UserRoleEnum
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

/**
 * 사용자 정보를 응답하기 위한 DTO로 변환하는 확장 함수
 * @return UserResponseDto 변환된 DTO
 */
fun User.toResponse() : UserResponseDto {
    return UserResponseDto(
        userName = name,
        userRole = role.name
    )
}
