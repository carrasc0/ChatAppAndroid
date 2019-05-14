package com.example.mvvmtest.manager;

import com.example.mvvmtest.util.Zodiac;

public class ZodiacManager {

    public static Zodiac getZodiac(int day, int month) {
        switch (month) {
            case 1:
                if (day >= 21) {
                    return Zodiac.ACUARIO;
                } else {
                    return Zodiac.CAPRICORIO;
                }
            case 2:
                if (day >= 20) {
                    return Zodiac.PISCIS;
                } else {
                    return Zodiac.ACUARIO;
                }
            case 3:
                if (day >= 21) {
                    return Zodiac.ARIES;
                } else {
                    return Zodiac.PISCIS;
                }
            case 4:
                if (day >= 20) {
                    return Zodiac.TAURO;
                } else {
                    return Zodiac.ARIES;
                }
            case 5:
                if (day >= 21) {
                    return Zodiac.GEMINIS;
                } else {
                    return Zodiac.TAURO;
                }
            case 6:
                if (day >= 22) {
                    return Zodiac.CANCER;
                } else {
                    return Zodiac.GEMINIS;
                }
            case 7:
                if (day >= 23) {
                    return Zodiac.LEO;
                } else {
                    return Zodiac.CANCER;
                }
            case 8:
                if (day >= 24) {
                    return Zodiac.VIRGO;
                } else {
                    return Zodiac.LEO;
                }
            case 9:
                if (day >= 24) {
                    return Zodiac.LIBRA;
                } else {
                    return Zodiac.VIRGO;
                }
            case 10:
                if (day >= 23) {
                    return Zodiac.ESCORPIO;
                } else {
                    return Zodiac.LIBRA;
                }
            case 11:
                if (day >= 22) {
                    return Zodiac.SAGITARIO;
                } else {
                    return Zodiac.ESCORPIO;
                }
            case 12:
                if (day >= 22) {
                    return Zodiac.CAPRICORIO;
                } else {
                    return Zodiac.SAGITARIO;
                }
        }
        return null;
    }

}
