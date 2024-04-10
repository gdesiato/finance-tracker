package com.gdesiato.financetracker.model

import jakarta.persistence.*

@Entity
@Table(name = "category")
data class Category(

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,
    val name: String
)