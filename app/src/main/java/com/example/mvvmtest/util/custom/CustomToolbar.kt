package com.example.mvvmtest.util.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.Toolbar

class CustomToolbar(context: Context?, attrs: AttributeSet?) : Toolbar(context, attrs) {

    override fun addView(child: View?) {
        super.addView(child)
    }
}