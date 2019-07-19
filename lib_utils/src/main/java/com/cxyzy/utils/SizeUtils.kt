package com.cxyzy.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Point

/**
 * size convert utils
 */
object SizeUtils {

    fun px2dp(context: Context, pxValue: Float): Int {
        val density = context.resources.displayMetrics.density
        return (pxValue / density + 0.5f).toInt()
    }

    fun dp2px(context: Context, dpValue: Float): Int {
        val density = context.resources.displayMetrics.density
        return (dpValue * density + 0.5f).toInt()
    }

    fun px2sp(context: Context, pxValue: Float): Int {
        val scaleDensity = context.resources.displayMetrics.scaledDensity
        return (pxValue / scaleDensity + 0.5f).toInt()
    }

    fun sp2px(context: Context, spValue: Float): Int {
        val scaleDensity = context.resources.displayMetrics.scaledDensity
        return (spValue * scaleDensity + 0.5f).toInt()
    }
}
