package com.cxyzy.demo.log

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cxyzy.demo.R
import com.cxyzy.utils.*
import com.cxyzy.utils.ext.toast
import kotlinx.android.synthetic.main.activity_log.*

class LogUtilsDemoActivity : AppCompatActivity(), LogUtils {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)
        initViews()
    }

    private fun initViews() {
        logBtn.setOnClickListener {
            verbose(editText.text)
            debug(editText.text)
            info(editText.text)
            warn(editText.text)
            try {
                throw Exception(editText.text.toString())
            } catch (e: Exception) {
                error(e)
            }
            toast("Logcat tag: " + LogUtilsDemoActivity::class.java.simpleName)
        }
    }
}
