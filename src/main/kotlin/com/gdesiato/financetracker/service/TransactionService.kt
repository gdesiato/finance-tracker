package com.gdesiato.financetracker.service

import com.gdesiato.financetracker.model.Transaction
import com.gdesiato.financetracker.repository.TransactionRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class TransactionService @Autowired constructor(private val transactionRepository: TransactionRepository) {
    fun getAllTransactions(): List<Transaction> = transactionRepository.findAll()

    fun getTransactionById(transactionId: Long): Transaction? =
        transactionRepository.findById(transactionId).orElse(null)

    fun getTransactionsByDateRange(startDate: LocalDate, endDate: LocalDate): List<Transaction> =
        transactionRepository.findByDateBetween(startDate, endDate)

    @Transactional
    fun addTransaction(transaction: Transaction): Transaction = transactionRepository.save(transaction)

    @Transactional
    fun updateTransaction(transactionId: Long, updatedTransaction: Transaction): Transaction? {
        return if (transactionRepository.existsById(transactionId)) {
            transactionRepository.save(updatedTransaction.copy(id = transactionId))
        } else null
    }

    @Transactional
    fun deleteTransaction(transactionId: Long): Boolean {
        return if (transactionRepository.existsById(transactionId)) {
            transactionRepository.deleteById(transactionId)
            true
        } else false
    }
}