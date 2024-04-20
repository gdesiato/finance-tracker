package com.gdesiato.financetracker.repository

import com.gdesiato.financetracker.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

}
