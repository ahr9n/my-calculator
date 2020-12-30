package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mycalculator.databinding.ActivityMainBinding
import java.lang.Exception
import kotlin.math.max

class MainActivity : AppCompatActivity() {

    private var s: String = ""
    private val ops: String = "+-*/"

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.outText.setText(s)

        numericalButtons()
        operationalButtons()
        others()

        // a4tar katkot :D
    }

    private fun numericalButtons(){
        // NumericalButtons()
        binding.outText.apply {
            binding.bu0.setOnClickListener{ // TODO: I don't want leading zeroes
                if(s[s?.length-1] != '0') s += "0"
                this.setText(s)
            }

            binding.bu1.setOnClickListener{
                if(s == "0") s?.dropLast(1)
                s += "1"
                this.setText(s)
            }

            binding.bu2.setOnClickListener{
                if(s == "0") s?.dropLast(1)
                s += "2"
                this.setText(s)
            }

            binding.bu3.setOnClickListener{
                if(s == "0") s?.dropLast(1)
                s += "3"
                this.setText(s)
            }

            binding.bu4.setOnClickListener{
                if(s == "0") s?.dropLast(1)
                s += "4"
                this.setText(s)
            }

            binding.bu5.setOnClickListener{
                if(s == "0") s?.dropLast(1)
                s += "5"
                this.setText(s)
            }

            binding.bu6.setOnClickListener{
                if(s == "0") s?.dropLast(1)
                s += "6"
                this.setText(s)
            }

            binding.bu7.setOnClickListener{
                if(s == "0") s?.dropLast(1)
                s += "7"
                this.setText(s)
            }

            binding.bu8.setOnClickListener{
                if(s == "0") s?.dropLast(1)
                s += "8"
                this.setText(s)
            }

            binding.bu9.setOnClickListener{
                if(s == "0") s?.dropLast(1)
                s += "9"
                this.setText(s)
            }

            binding.buDot.setOnClickListener{ // TODO: no more than one dot
                var lst: Int = 0
                lst = max(lst, s.lastIndexOfAny(ops.toCharArray()))
                if(!s!!.substring(lst, s.length-1).contains('.')) s += "."
                this.setText(s)
            }
        }
    }

    private fun operationalButtons(){
        binding.outText.apply {
            binding.buPlus.setOnClickListener{
                if(ops.contains(s!!.last()) || s!!.last()=='.')
                    s = s?.dropLast(1)
                s += "+"
                this.setText(s)
            }
            binding.buMinus.setOnClickListener{
                if(ops.contains(s!!.last()) || s!!.last()=='.')
                    s = s?.dropLast(1)

                s += "-"
                this.setText(s)
            }
            binding.buMultiply.setOnClickListener{
                if(ops.contains(s!!.last()) || s!!.last()=='.')
                    s = s?.dropLast(1)

                s += "*"
                this.setText(s)
            }
            binding.buDivide.setOnClickListener{
                if(ops.contains(s!!.last()) || s!!.last()=='.')
                    s = s?.dropLast(1)

                s += "/"
                this.setText(s)
            }

            binding.buResult.setOnClickListener{
                if(ops.contains(s!!.last()) || s!!.last()=='.') s?.dropLast(1)

                try{
                    s = solve(s!!).toString()
                    this.setText(s)
                }catch (e: Exception){
                    this.setText("Error")
                }
                // s = "0"
            }
        }
    }

    private fun others(){
        binding.outText.apply {
            binding.buPercent.setOnClickListener{
                if(ops.contains(s!!.last()) || s!!.last()=='.')
                    s?.dropLast(1)

                s += "/100"
                this.setText(s)
            }

            binding.buAC.setOnClickListener{
                Log.d("Hayssamtest", "MainActivity:others: uAC")
                when
                {
                    s.isNotEmpty() ->
                    {
                        s = s.dropLast(1)
                    }
                    s.isEmpty() ->
                    {
                        s = "0"
                    }
                }
                this.setText(s)
            }
        }
    }

    private fun solve(s: String): Double{

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
