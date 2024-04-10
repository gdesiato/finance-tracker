package com.gdesiato.financetracker.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "transaction_entry")
data class Transaction(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,
    val amount: Double,

    @ManyToOne
    val category: Category,

    val date: LocalDate,
    val description: String? = null
)