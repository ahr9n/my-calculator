package com.example.mycalculator

import java.util.*

class SolveEquation {

    companion object {
        private val opers: String = "+-×÷"
        private var values = Stack<Double>()
        private var ops = Stack<Char>()

        fun getAnswer(tokens: String): Double {
            values.clear()
            ops.clear()

            var i = 0
            while (i < tokens.length) {
                when {
                    (tokens[i].isDigit() || tokens[i] == '.') -> {
                        val num = StringBuffer()
                        while (i < tokens.length && (tokens[i].isDigit() || tokens[i] == '.'))
                            num.append(tokens[i++])
                        values.push(num.toString().toDouble())
                        i--
                    }
                    opers.contains(tokens[i]) -> {
                        while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                            values.push(applyOp(ops.pop(), values.pop(), values.pop()))
                        ops.push(tokens[i])
                    }
                }
                i++
            }

            while (!ops.empty()) values.push(applyOp(ops.pop(), values.pop(), values.pop()))
            return ("%.4f".format(values.pop())).toDouble()
        }

        private fun hasPrecedence(op1: Char, op2: Char): Boolean {
            return !((op1 == '×' || op1 == '÷') && (op2 == '+' || op2 == '-'))
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
    }

    // SolveEquation.getAnswer(tokens)
}
