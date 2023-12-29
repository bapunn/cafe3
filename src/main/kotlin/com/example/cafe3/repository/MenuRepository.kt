package com.example.cafe3.repository

import com.example.cafe3.model.Menu
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface MenuRepository : JpaRepository<Menu, Long>{

    @Query("select m from Menu m where m.id in :idList")
    fun findMenuListById(idList: List<Long>) :List<Menu>
}