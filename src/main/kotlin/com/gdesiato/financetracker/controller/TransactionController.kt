package com.gdesiato.financetracker.controller

import com.gdesiato.financetracker.model.Transaction
import com.gdesiato.financetracker.service.TransactionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/transactions")
class TransactionController @Autowired constructor(private val transactionService: TransactionService) {

    @GetMapping
    fun getAllTransactions(): ResponseEntity<List<Transaction>> =
        ResponseEntity.ok(transactionService.getAllTransactions())

    @GetMapping("/{id}")
    fun getTransactionById(@PathVariable id: Long): ResponseEntity<Transaction> =
        transactionService.getTransactionById(id)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()

    @GetMapping("/dateRange")
    fun getTransactionsByDateRange(
        @RequestParam startDate: LocalDate,
        @RequestParam endDate: LocalDate
    ): ResponseEntity<List<Transaction>> =
        ResponseEntity.ok(transactionService.getTransactionsByDateRange(startDate, endDate))

    @PostMapping
    fun addTransaction(@RequestBody transaction: Transaction): ResponseEntity<Transaction> =
        ResponseEntity.status(HttpStatus.CREATED).body(transactionService.addTransaction(transaction))

    @PutMapping("/{id}")
    fun updateTransaction(
        @PathVariable id: Long,
        @RequestBody updatedTransaction: Transaction
    ): ResponseEntity<Transaction> =
        transactionService.updateTransaction(id, updatedTransaction)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()

    @DeleteMapping("/{id}")
    fun deleteTransaction(@PathVariable id: Long): ResponseEntity<Void> =
        if (transactionService.deleteTransaction(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
}