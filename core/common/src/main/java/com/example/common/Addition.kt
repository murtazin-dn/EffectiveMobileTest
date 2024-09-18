package com.example.common

fun getAddition(num: Int, one: Int, two: Int, three: Int): Int {
    val preLastDigit = num % 100 / 10

    if (preLastDigit == 1) {
        return three
    }

    return when (num % 10) {
        1 -> one
        2, 3, 4 -> two
        else -> three
    }
}