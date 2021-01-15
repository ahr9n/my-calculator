package com.example.mycalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import java.lang.Exception
import kotlin.math.max

class MainActivity : AppCompatActivity() {

    var s: String = ""
    private val ops: String = "+-*/"
    lateinit var display: TextView

    lateinit var nums: Array<Button>
    lateinit var dot: Button

    lateinit var res: Button
    lateinit var divide: Button
    lateinit var multiply: Button
    lateinit var minus: Button
    lateinit var plus: Button
    lateinit var plusMinus: Button

    lateinit var percent: Button
    lateinit var delete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.outText)
        display.text = s // initially

        numericalButtons()
        operationButtons()
        others()

        // NumericalButtons()
        nums[0].setOnClickListener { // TODO: I don't want leading zeroes
            if (s.last() != '0') s += "0"
            display.text = s
        }

        for (i in 1..9) {
            nums[i].setOnClickListener {
                if (s[0] == '0' && s.length == 1) s.dropLast(1)
                s += i.toString()
                display.text = s
            }
        }
        dot.setOnClickListener { // TODO: no more than one dot
            var lst: Int = 0
            lst = max(lst, s.lastIndexOfAny(ops.toCharArray()))
            if (!s.substring(lst, s.length - 1).contains('.')) s += "."
            display.text = s
        }

        // operationButtons()
        // TODO: no two consecutive elements are operators
        plus.setOnClickListener {
            if (ops.contains(s.last()) || s.last() == '.')
                s.dropLast(1)
            s += "+"
            display.text = s
        }
        minus.setOnClickListener {
            if (ops.contains(s.last()) || s.last() == '.')
                s.dropLast(1)

            s += "-"
            display.text = s
        }
        multiply.setOnClickListener {
            if (ops.contains(s.last()) || s.last() == '.')
                s.dropLast(1)

            s += "*"
            display.text = s
        }
        divide.setOnClickListener {
            if (ops.contains(s.last()) || s.last() == '.')
                s.dropLast(1)

            s += "/"
            display.text = s
        }

        res.setOnClickListener {
            if (ops.contains(s.last()) || s.last() == '.') s.dropLast(1)

            try {
                s = solve(s).toString()
                display.text = s
            } catch (e: Exception) {
                display.text = "Error"
            }
            // s = "0"
        }

        // others()
        percent.setOnClickListener {
            if (ops.contains(s.last()) || s.last() == '.')
                s.dropLast(1)

            s += "/100"
            display.text = s
        }
        delete.setOnClickListener {
            if (s.isNotEmpty())
                s = s.dropLast(1)
            if (s.isEmpty())
                s = "0"
            display.text = s
        }

        // a4tar katkot :D
    }

    private fun numericalButtons() {
        nums = Array(10) {
            val id = resources.getIdentifier("bu$it", "id", packageName)
            findViewById<Button>(id)
        }

        dot = findViewById(R.id.buDot)
    }

    private fun operationButtons() {
        res = findViewById<Button>(R.id.buResult)
        divide = findViewById<Button>(R.id.buDivide)
        multiply = findViewById<Button>(R.id.buMultiply)
        minus = findViewById<Button>(R.id.buMinus)
        plus = findViewById<Button>(R.id.buPlus)
        plusMinus = findViewById<Button>(R.id.buPlusMinus)
    }

    private fun others() {
        percent = findViewById<Button>(R.id.buPercent)
        delete = findViewById<Button>(R.id.buAC)
    }

    private fun solve(s: String): Double {

        val equation: Array<String> = Array(s.length) { i -> "" }
        // var ops: String = "+-*/"
        var start: Int = 0
        var cnt: Int = 0

        for (i in s.indices)
            if (ops.contains(s[i])) {
                equation[cnt++] = s.substring(start, i)
                equation[cnt++] = s[i].toString()

                start = i + 1
            }
        equation[cnt++] = s.substring(start, s.length - 1)

        var answer: Double = equation[0].toDouble()
        loop@ for (i in 1..equation.size - 2 step 2)
            when (equation[i]) {
                "+" -> {
                    answer += equation[i + 1].toDouble()
                }
                "-" -> {
                    answer -= equation[i + 1].toDouble()
                }
                "/" -> {
                    answer /= equation[i + 1].toDouble()
                }
                "*" -> {
                    answer *= equation[i + 1].toDouble()
                }
                else -> {
                    break@loop
                }
            }

        return answer
    }

}
