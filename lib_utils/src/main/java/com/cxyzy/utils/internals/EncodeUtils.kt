package com.cxyzy.utils.internals

import android.util.Base64

/**
 * encode utils
 */
object EncodeUtils {

    /**
     * Return Base64-encode string.
     *
     * @param input The input.
     * @return Base64-encode string
     */
    fun base64Encode2String(input: ByteArray?): String {
        return if (input == null || input.isEmpty()) "" else Base64.encodeToString(input, Base64.NO_WRAP)
    }
}
