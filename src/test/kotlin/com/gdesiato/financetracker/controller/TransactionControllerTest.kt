package com.gdesiato.financetracker.controller

import com.gdesiato.financetracker.model.Category
import com.gdesiato.financetracker.model.Transaction
import com.gdesiato.financetracker.repository.CategoryRepository
import com.gdesiato.financetracker.repository.TransactionRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDate

@SpringBootTest
@AutoConfigureMockMvc
class TransactionControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var transactionRepository: TransactionRepository

    @Autowired
    private lateinit var categoryRepository: CategoryRepository


    @Test
    fun `getAllTransactions should return all transactions and status OK`() {
        // Given
        val category = Category(name = "Utilities")
        categoryRepository.save(category)

        val transaction1 = Transaction(amount = 100.0, category = category, date = LocalDate.now(), description = "Electric bill")
        val transaction2 = Transaction(amount = 150.0, category = category, date = LocalDate.now(), description = "Water bill")
        transactionRepository.save(transaction1)
        transactionRepository.save(transaction2)

        // When & Then
        mockMvc.perform(get("/transactions"))
            .andExpect(status().isOk)
    }
}
