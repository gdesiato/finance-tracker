package com.gdesiato.financetracker.repository

import com.gdesiato.financetracker.model.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : JpaRepository<Category, Long> {
}
