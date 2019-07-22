package com.cxyzy.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cxyzy.demo.log.LogUtilsDemoActivity
import com.cxyzy.demo.toast.ToastUtilsDemoActivity
import com.cxyzy.utils.ext.startActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        logUtilBtn.setOnClickListener { startActivity<LogUtilsDemoActivity>() }
        toastUtilBtn.setOnClickListener { startActivity<ToastUtilsDemoActivity>() }
    }
}
