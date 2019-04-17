package com.cxyzy.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cxyzy.kt.utils.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toast("test")
    }
}
