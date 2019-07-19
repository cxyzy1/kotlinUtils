package com.cxyzy.kt.utils


import com.cxyzy.utils.internals.Utils

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import androidx.test.core.app.ApplicationProvider
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLog


@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, shadows = [ShadowLog::class])
open class BaseTest {
    init {
        ShadowLog.stream = System.out
        Utils.init(ApplicationProvider.getApplicationContext())
    }

    @Test
    @Throws(Exception::class)
    fun test() {

    }
}
