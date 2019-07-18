package com.cxyzy.kt.utils


import com.cxyzy.utils.blankj.Utils

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLog


/**
 * <pre>
 * author: Blankj
 * blog  : http://blankj.com
 * time  : 2018/08/03
 * desc  :
</pre> *
 */
@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, shadows = [ShadowLog::class])
open class BaseTest {
    init {
        ShadowLog.stream = System.out
        Utils.init(RuntimeEnvironment.application)
    }

    @Test
    @Throws(Exception::class)
    fun test() {

    }
}
