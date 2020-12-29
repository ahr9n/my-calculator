package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.Exception

var s: String ?= null

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        s = "0" // initially

        val display: TextView = findViewById(R.id.outText)
        display.text = s
    }

    fun buttonEvent(view: View?, display: TextView){

        var ops: String = "+-*/"
        var clicked = view as Button

        when(clicked.id){
            R.id.bu0 -> {
                // TODO: I don't want leading zeroes
                if(s!![s.length-1] != '0') s += "0"
                display.text = s
            }
            R.id.bu1 -> {
                s += "1"
                display.text = s
            }
            R.id.bu2 -> {
                s += "2"
                display.text = s
            }
            R.id.bu3 -> {
                s += "3"
                display.text = s
            }
            R.id.bu4 -> {
                s += "4"
                display.text = s
            }
            R.id.bu5 -> {
                s += "5"
                display.text = s
            }
            R.id.bu6 -> {
                s += "6"
                display.text = s
            }
            R.id.bu7 -> {
                s += "7"
                display.text = s
            }
            R.id.bu8 -> {
                s += "8"
                display.text = s
            }
            R.id.bu9 -> {
                s += "9"
                display.text = s
            }

            // TODO: no more than one dot
            R.id.buDot -> {
                if(!s!!.contains('.')) s += "."
                display.text = s
            }

            // TODO: no two consecutive elements are operators
            R.id.buPlus -> {
                if(ops.contains(s!!.last())){
                    s?.dropLast(1)
                    s += "+"
                }
                display.text = s
            }
            R.id.buMinus -> {
                if(ops.contains(s!!.last())){
                    s?.dropLast(1)
                    s += "-"
                }
                display.text = s
            }
            R.id.buMultiply -> {
                if(ops.contains(s!!.last())){
                    s?.dropLast(1)
                    s += "*"
                }
                display.text = s
            }
            R.id.buDivide -> {
                if(ops.contains(s!!.last())){
                    s?.dropLast(1)
                    s += "/"
                }
                display.text = s
            }

            R.id.buAC -> {
                if(s!!.isNotEmpty()){
                    s?.dropLast(1)
                }
                display.text = s
            }
            R.id.buResult -> {
                if(ops.contains(s!!.last())) s?.dropLast(1)

                try{
                    display.text = solve(s!!).toString()
                }catch (e: Exception){
                    display.text = "Error"
                }
                s = "0"
            }
            /// a4tar katkot :D
        }
    }

    fun solve(s: String): Double{

        var equation: Array<String> = Array(s.length) { i -> "" }
        var ops: String = "+-*/"
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
