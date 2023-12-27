package com.example.composedemo

data class CalculatorState(
    val leftNumber: String = "",
    val rightNumber: String = "",
    val operator: CalculatorOperation? = null
)

