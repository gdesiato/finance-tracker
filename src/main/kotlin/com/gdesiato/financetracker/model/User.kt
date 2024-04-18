package com.gdesiato.financetracker.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false)
    val email: String,

    @Column(nullable = false)
    val password: String,
    
)
