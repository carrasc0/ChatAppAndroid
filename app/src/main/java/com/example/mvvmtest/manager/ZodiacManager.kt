package com.example.mvvmtest.manager

import com.example.mvvmtest.util.Zodiac

object ZodiacManager {

    fun getZodiac(day: Int, month: Int): Zodiac? {
        when (month) {
            1 -> return if (day >= 21) {
                Zodiac.ACUARIO
            } else {
                Zodiac.CAPRICORIO
            }
            2 -> return if (day >= 20) {
                Zodiac.PISCIS
            } else {
                Zodiac.ACUARIO
            }
            3 -> return if (day >= 21) {
                Zodiac.ARIES
            } else {
                Zodiac.PISCIS
            }
            4 -> return if (day >= 20) {
                Zodiac.TAURO
            } else {
                Zodiac.ARIES
            }
            5 -> return if (day >= 21) {
                Zodiac.GEMINIS
            } else {
                Zodiac.TAURO
            }
            6 -> return if (day >= 22) {
                Zodiac.CANCER
            } else {
                Zodiac.GEMINIS
            }
            7 -> return if (day >= 23) {
                Zodiac.LEO
            } else {
                Zodiac.CANCER
            }
            8 -> return if (day >= 24) {
                Zodiac.VIRGO
            } else {
                Zodiac.LEO
            }
            9 -> return if (day >= 24) {
                Zodiac.LIBRA
            } else {
                Zodiac.VIRGO
            }
            10 -> return if (day >= 23) {
                Zodiac.ESCORPIO
            } else {
                Zodiac.LIBRA
            }
            11 -> return if (day >= 22) {
                Zodiac.SAGITARIO
            } else {
                Zodiac.ESCORPIO
            }
            12 -> return if (day >= 22) {
                Zodiac.CAPRICORIO
            } else {
                Zodiac.SAGITARIO
            }
        }
        return null
    }

}
