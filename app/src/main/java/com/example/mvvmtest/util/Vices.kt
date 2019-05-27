package com.example.mvvmtest.util

enum class Vices {
    YES {
        fun wordToString(): String = "Si"
    },
    NO,
    SOMETIMES,
    PREFER_NO_TO_SAY
}
