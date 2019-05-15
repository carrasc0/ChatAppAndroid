package com.example.mvvmtest.util;

import com.example.mvvmtest.R;

public enum Zodiac {
    CAPRICORIO(R.drawable.capricornius),
    ACUARIO(R.drawable.aquarius),
    PISCIS(R.drawable.pisces),
    ARIES(R.drawable.aries),
    TAURO(R.drawable.taurus),
    GEMINIS(R.drawable.gemini),
    CANCER(R.drawable.cancer),
    LEO(R.drawable.leo),
    VIRGO(R.drawable.virgo),
    LIBRA(R.drawable.libra),
    ESCORPIO(R.drawable.scorpius),
    SAGITARIO(R.drawable.sagittarius);

    private final int resourceId;

    Zodiac(int resourceId) {
        this.resourceId = resourceId;
    }

    public int getResourceId() {
        return resourceId;
    }
}
