package com.cxyzy.utils.ext

import android.view.View

fun View.show(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.show() = show(true)

fun View.hide() = show(false)