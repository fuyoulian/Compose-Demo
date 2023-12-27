package com.example.composedemo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel: ViewModel() {
    var state by mutableStateOf(CalculatorState())

    fun onEvents(event: CalculatorEvent) {
        when(event) {
            CalculatorEvent.Calculator -> performCalcutor()
            CalculatorEvent.Clear -> performClear()
            CalculatorEvent.Decimal -> performDecimal()
            CalculatorEvent.Delete -> performDelete()
            is CalculatorEvent.Number -> writeNumber(event.number)
            is CalculatorEvent.Operation -> writeOperation(event.operation)
        }
    }

    private fun performCalcutor() {
        val num1 = state.leftNumber.toDoubleOrNull()
        val num2 = state.rightNumber.toDoubleOrNull()
        if (num1 != null && num2 != null) {
            val res = when (state.operator) {
                CalculatorOperation.Add -> num1 + num2
                CalculatorOperation.Divide -> num1 / num2
                CalculatorOperation.Multiply -> num1 * num2
                CalculatorOperation.Subtract -> num1 - num2
                null -> return
            }
            state = state.copy(leftNumber = res.toString().take(15), operator = null, rightNumber = "")
        }
    }

    private fun performClear() {
        state = CalculatorState()
    }

    private fun performDecimal() {
        if (state.operator == null
            && !state.leftNumber.contains(".")
            && state.leftNumber.isNotBlank()
        ) {
            state = state.copy(leftNumber = state.leftNumber + ".")
            return
        }
        if (state.operator != null
            && !state.rightNumber.contains(".")
            && state.rightNumber.isNotBlank()
        ) {
            state = state.copy(rightNumber = state.rightNumber + ".")
            return
        }
    }

    private fun performDelete() {
        when {
            state.rightNumber.isNotBlank() -> state.copy(rightNumber = state.rightNumber.dropLast(1))
            state.operator != null -> state.copy(operator = null)
            state.leftNumber.isNotBlank() -> state.copy(leftNumber = state.leftNumber.dropLast(1))
        }
    }

    private fun writeOperation(operation: CalculatorOperation) {
        if (state.leftNumber.isNotBlank()) {
            state = state.copy(operator = operation)
        }
    }

    private fun writeNumber(number: Int) {
        if (state.operator == null) {
            if (state.leftNumber.length >= 8) return
            state = state.copy(leftNumber = state.leftNumber + number)
            return
        }
        if (state.rightNumber.length >= 8) return
        state = state.copy(rightNumber = state.rightNumber + number)
    }

}