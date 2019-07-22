package com.cxyzy.demo.log

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cxyzy.demo.R
import com.cxyzy.utils.LogUtil
import com.cxyzy.utils.ext.toast
import com.cxyzy.utils.info
import kotlinx.android.synthetic.main.activity_log.*

class LogUtilsDemoActivity : AppCompatActivity(), LogUtil {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)
        initViews()
    }

    private fun initViews() {
        logBtn.setOnClickListener {
            info(editText.text)
            toast("Logcat tag: " + LogUtilsDemoActivity::class.java.simpleName)
        }
    }
}
