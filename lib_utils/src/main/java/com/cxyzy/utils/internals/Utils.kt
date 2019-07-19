package com.cxyzy.utils.internals

import android.annotation.SuppressLint
import android.app.Application

import java.lang.reflect.InvocationTargetException

object Utils {
    @SuppressLint("StaticFieldLeak")
    private var sApplication: Application? = null

    val app: Application
        get() {
            if (sApplication != null) return sApplication as Application
            val app = applicationByReflect
            init(app)
            return app
        }

    private val applicationByReflect: Application
        get() {
            try {
                @SuppressLint("PrivateApi")
                val activityThread = Class.forName("android.app.ActivityThread")
                val thread = activityThread.getMethod("currentActivityThread").invoke(null)
                val app = activityThread.getMethod("getApplication").invoke(thread)
                        ?: throw NullPointerException("u should init first")
                return app as Application
            } catch (e: Exception) {
                e.printStackTrace()
            }

            throw NullPointerException("u should init first")
        }

    /**
     * Init utils.
     *
     * Init it in the class of Application.
     *
     * @param app application
     */
    fun init(app: Application?) {
        if (sApplication == null) {
            sApplication = app ?: applicationByReflect
        } else {
            if (app != null && app.javaClass != sApplication!!.javaClass) {
                sApplication = app
            }
        }
    }

}
