package com.example.mycalculator

import java.util.*

class SolveEquation {

    private val opers: String = "+-*/"
    private lateinit var values: Stack<Double>
    private lateinit var ops: Stack<Char>

    private fun getAnswer(tokens: String): Double{
        values.clear()
        ops.clear()

        var i = 0
        while (i < tokens.length) {
            if (tokens[i].isDigit()) {
                val num = StringBuffer()
                while (i < tokens.length && tokens[i].isDigit()) num.append(tokens[i++])
                values.push(num.toString().toDouble())
                i--
            }else if (tokens[i] == '('){
                ops.push(tokens[i])
            }else if (tokens[i] == ')') {
                while (ops.peek() != '(')
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()))
                ops.pop()
            }else if (opers.contains(tokens[i])) {
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()))
                ops.push(tokens[i])
            }
            i++
        }

        while (!ops.empty()) values.push(applyOp(ops.pop(), values.pop(), values.pop()))
        return values.pop()
    }

    private fun hasPrecedence(op1: Char, op2: Char): Boolean {
        if (op2 == '(' || op2 == ')') return false
        return !((op1 == '×' || op1 == '÷') &&  (op2 == '+' || op2 == '-'))
    }

    private fun applyOp(op: Char, b: Double, a: Double): Double {
        when (op) {
            '+' -> return a + b
            '-' -> return a - b
            '×' -> return a * b
            '÷' -> {
                if (b == 0.0) throw UnsupportedOperationException("Error: Cannot divide by zero!")
                return a / b
            }
        }
        return 0.0
    }

    // SolveEquation.getAnswer()
}
