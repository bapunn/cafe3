package com.example.cafe3.service

import com.example.cafe3.dto.requestDto.ChargeRequestDto
import com.example.cafe3.dto.responseDto.ChargeResponseDto
import com.example.cafe3.dto.requestDto.UserRequestDto
import com.example.cafe3.dto.responseDto.UserResponseDto
import com.example.cafe3.enums.UserRoleEnum
import com.example.cafe3.exception.CustomException
import com.example.cafe3.exception.ErrorCode
import com.example.cafe3.model.User
import com.example.cafe3.model.toResponse
import com.example.cafe3.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository
) {

    @Transactional
    fun createUser(userRequest: UserRequestDto): UserResponseDto {
        val newUser = User(
            name = userRequest.userName,
            password = userRequest.password,
            role = UserRoleEnum.USER
        )
        return userRepository.save(newUser).toResponse()
    }

    @Transactional
    fun addBalance(
        chargeRequest: ChargeRequestDto, userId: Long
    ): ChargeResponseDto {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw CustomException("user", ErrorCode.NOT_FOUND)
        user.balance += chargeRequest.chargeAmount

        return ChargeResponseDto(
            userId = user.id!!,
            userName = user.name,
            chargeAmount = chargeRequest.chargeAmount,
            balance = user.balance
        )


    }



}
