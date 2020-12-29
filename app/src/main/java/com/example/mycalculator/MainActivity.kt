package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.Exception

var s: String = "";

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buttonEvent(view: View?){

        val display: TextView = findViewById(R.id.outText);

        var clicked = view as Button
        when(clicked.id){
            R.id.bu0 -> {
                // TODO: I don't want leading zeroes
                s += "0"
                display.setText(s)
            }
            R.id.bu1 -> {
                s += "1"
                display.setText(s)
            }
            R.id.bu2 -> {
                s += "2"
                display.setText(s)
            }
            R.id.bu3 -> {
                s += "3"
                display.setText(s)
            }
            R.id.bu4 -> {
                s += "4"
                display.setText(s)
            }
            R.id.bu5 -> {
                s += "5"
                display.setText(s)
            }
            R.id.bu6 -> {
                s += "6"
                display.setText(s)
            }
            R.id.bu7 -> {
                s += "7"
                display.setText(s)
            }
            R.id.bu8 -> {
                s += "8"
                display.setText(s)
            }
            R.id.bu9 -> {
                s += "9"
                display.setText(s)
            }
            R.id.buDot -> {
                // TODO: I don't want more than one dot
                s += "."
                display.setText(s)
            }
            R.id.buPlus -> {
                s += "+"
                display.setText(s)
            }
            R.id.buMinus -> {
                s += "-"
                display.setText(s)
            }
            R.id.buMultiply -> {
                s += "*"
                display.setText(s)
            }
            R.id.buDivide -> {
                s += "/"
                display.setText(s)
            }
            R.id.buAC -> {
                if(s.length > 0){
                    s = s.substring(0, s.length-1)
                }
                display.setText(s)
            }
            R.id.buResult -> {
                try{
                    display.setText(solve(s).toString())
                }catch (e: Exception){
                    display.setText("Error")
                }
                s = "0"
            }
            /// wait
        }
    }

    fun solve(s: String): Double{
        var equation: Array<String> = Array(s.length) { i -> "" }
        var ops: String = "+-*/"
        var start: Int = 0
        var cnt: Int = 0

        for(i in 0 until s.length-1)
            if(ops.contains(s.substring(i, i+1))){

                equation.set(cnt++, s.substring(start, i))
                equation.set(cnt++, s.substring(i, i+1))

                start = i+1
            }
        equation.set(cnt++, s.substring(start, s.length))

        var answer: Double = equation.get(0).toDouble()
        for(i in 1..equation.size-2 step 2)
            when(equation.get(i)){
                "+" -> {
                    answer += equation.get(i+1).toDouble()
                }
                "-" -> {
                    answer -= equation.get(i+1).toDouble()
                }
                "/" -> {
                    answer /= equation.get(i+1).toDouble()
                }
                "*" -> {
                    answer *= equation.get(i+1).toDouble()
                }
            }

        return answer
    }

}
