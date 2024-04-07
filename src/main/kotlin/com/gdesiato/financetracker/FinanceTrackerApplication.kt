package com.gdesiato.financetracker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FinanceTrackerApplication

fun main(args: Array<String>) {
	runApplication<FinanceTrackerApplication>(*args)
}
