package com.example.mvvmtest;


import android.content.SharedPreferences;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Calendar;

/**
 * Unit tests for the {@link com.example.mvvmtest.manager.FlechPreferences}
 * that mocks {@link SharedPreferences}.
 */
@RunWith(MockitoJUnitRunner.class)
public class SharedPreferencesTest {

    private static final String TEST_NAME = "Test Flech Preferences";
    private static final String TEST_EMAIL = "gbc@gmail.com";

    private static final Calendar TEST_DATE_OF_BIRTH = Calendar.getInstance();

}
