package com.cxyzy.demo.toast

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cxyzy.demo.R
import com.cxyzy.utils.ext.toast
import kotlinx.android.synthetic.main.activity_toast.*

class ToastUtilsDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toast)
        initViews()
    }

    private fun initViews() {
        toastBtn.setOnClickListener {
            toast("hello")
        }
    }
}
