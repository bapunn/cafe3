package com.example.cafe3.repository

import com.example.cafe3.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {

}
