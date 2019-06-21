package com.example.chatapp.util

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun Any?.toString(): String {
    if (this == null) return "null"
    return toString()
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {

    this.addTextChangedListener(object : TextWatcher {

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {

        }

    })
}

fun EditText.onFocusChange(focusChange: (Boolean) -> Unit) {
    this.setOnFocusChangeListener { _, hasFocus ->
        focusChange.invoke(hasFocus)
    }


}

fun RecyclerView.onLayoutChange(layoutChange: (Boolean) -> Unit) {
    this.addOnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
        if (bottom < oldBottom) layoutChange.invoke(true)
    }
}
