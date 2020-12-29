package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.Exception
import kotlin.math.max

class MainActivity : AppCompatActivity() {

    var s: String = ""
    val ops: String = "+-*/"
    lateinit var display: TextView

    lateinit var zero: Button
    lateinit var one: Button
    lateinit var two: Button
    lateinit var three: Button
    lateinit var four: Button
    lateinit var five: Button
    lateinit var six: Button
    lateinit var seven: Button
    lateinit var eight: Button
    lateinit var nine: Button
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

        NumericalButtons()
        OperationButtons()
        others()

        // NumericalButtons()
        zero.setOnClickListener{ // TODO: I don't want leading zeroes
            if(s!![s!!.length-1] != '0') s += "0"
            display.text = s
        }
        one.setOnClickListener{
            if(s == "0") s?.dropLast(1)
            s += "1"
            display.text = s
        }
        two.setOnClickListener{
            if(s == "0") s?.dropLast(1)
            s += "2"
            display.text = s
        }
        three.setOnClickListener{
            if(s == "0") s?.dropLast(1)
            s += "3"
            display.text = s
        }
        four.setOnClickListener{
            if(s == "0") s?.dropLast(1)
            s += "4"
            display.text = s
        }
        five.setOnClickListener{
            if(s == "0") s?.dropLast(1)
            s += "5"
            display.text = s
        }
        six.setOnClickListener{
            if(s == "0") s?.dropLast(1)
            s += "6"
            display.text = s
        }
        seven.setOnClickListener{
            if(s == "0") s?.dropLast(1)
            s += "7"
            display.text = s
        }
        eight.setOnClickListener{
            if(s == "0") s?.dropLast(1)
            s += "8"
            display.text = s
        }
        nine.setOnClickListener{
            if(s == "0") s?.dropLast(1)
            s += "9"
            display.text = s
        }

        dot.setOnClickListener{ // TODO: no more than one dot
            var lst: Int = 0
            lst = max(lst, s.lastIndexOfAny(ops.toCharArray()))
            if(!s!!.substring(lst, s.length-1).contains('.')) s += "."
            display.text = s
        }

        // OperationButtons()
        // TODO: no two consecutive elements are operators
        plus.setOnClickListener{
            if(ops.contains(s!!.last()) || s!!.last()=='.')
                s?.dropLast(1)
            s += "+"
            display.text = s
        }
        minus.setOnClickListener{
            if(ops.contains(s!!.last()) || s!!.last()=='.')
                s?.dropLast(1)

            s += "-"
            display.text = s
        }
        multiply.setOnClickListener{
            if(ops.contains(s!!.last()) || s!!.last()=='.')
                s?.dropLast(1)

            s += "*"
            display.text = s
        }
        divide.setOnClickListener{
            if(ops.contains(s!!.last()) || s!!.last()=='.')
                s?.dropLast(1)

            s += "/"
            display.text = s
        }

        res.setOnClickListener{
            if(ops.contains(s!!.last()) || s!!.last()=='.') s?.dropLast(1)

            try{
                s = solve(s!!).toString()
                display.text = s
            }catch (e: Exception){
                display.text = "Error"
            }
            // s = "0"
        }

        // others()
        percent.setOnClickListener{
            if(ops.contains(s!!.last()) || s!!.last()=='.')
                s?.dropLast(1)

            s += "/100"
            display.text = s
        }
        delete.setOnClickListener{
            if(s!!.isNotEmpty())
                s?.dropLast(1)
            if(s.isEmpty())
                s = "0"
            display.text = s
        }

        // a4tar katkot :D
    }

    fun NumericalButtons(){
        zero = findViewById(R.id.bu0)
        one = findViewById(R.id.bu1)
        two = findViewById(R.id.bu2)
        three = findViewById(R.id.bu3)
        four = findViewById(R.id.bu4)
        five = findViewById(R.id.bu5)
        six = findViewById(R.id.bu6)
        seven = findViewById(R.id.bu7)
        eight = findViewById(R.id.bu8)
        nine = findViewById(R.id.bu9)
        dot = findViewById(R.id.buDot)
    }
    fun OperationButtons(){
        res = findViewById<Button>(R.id.buResult)
        divide = findViewById<Button>(R.id.buDivide)
        multiply = findViewById<Button>(R.id.buMultiply)
        minus = findViewById<Button>(R.id.buMinus)
        plus = findViewById<Button>(R.id.buPlus)
        plusMinus = findViewById<Button>(R.id.buPlusMinus)
    }
    fun others(){
        percent = findViewById<Button>(R.id.buPercent)
        delete = findViewById<Button>(R.id.buAC)
    }

    fun solve(s: String): Double{

        var equation: Array<String> = Array(s.length) { i -> "" }
        // var ops: String = "+-*/"
        var start: Int = 0
        var cnt: Int = 0

        for(i in s.indices)
            if(ops.contains(s[i])){

                equation[cnt++] = s.substring(start, i)
                equation[cnt++] = s[i].toString()

                start = i+1
            }
        equation[cnt++] = s.substring(start, s.length-1)

        var answer: Double = equation.get(0).toDouble()
        loop@ for(i in 1..equation.size-2 step 2)
            when(equation[i]){
                "+" -> {
                    answer += equation[i+1].toDouble()
                }
                "-" -> {
                    answer -= equation[i+1].toDouble()
                }
                "/" -> {
                    answer /= equation[i+1].toDouble()
                }
                "*" -> {
                    answer *= equation[i+1].toDouble()
                }
                else -> {
                    break@loop
                }
            }

        return answer
    }

}
