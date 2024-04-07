package com.gdesiato.financetracker.repository

import com.gdesiato.financetracker.model.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface TransactionRepository : JpaRepository<Transaction, Long> {

    fun findByDateBetween(startDate: LocalDate, endDate: LocalDate): List<Transaction>
}