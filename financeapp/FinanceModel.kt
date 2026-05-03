package com.example.financeapp 

import java.io.Serializable

// Model მონაცემების გადასაცემად
data class FinanceModel(
    val salary: Double,
    val rent: Double,
    val food: Double
) : Serializable
