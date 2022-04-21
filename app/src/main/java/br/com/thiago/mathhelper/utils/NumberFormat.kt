package br.com.thiago.mathhelper.utils

import android.util.Log

class NumberFormat {

    fun format(number: Double): String {
        val numberInt = number.toInt()
        val numberDouble: Double = number - numberInt
        Log.d("slabro", numberInt.toString())
        Log.d("slabro", numberDouble.toString())
        if (numberDouble == 0.0) return numberInt.toString()
        return String.format("%.2f", number)
    }

}