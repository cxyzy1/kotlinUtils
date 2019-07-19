package com.cxyzy.utils.internals;

import android.util.Base64;

/**
 * encode utils
 */
public final class EncodeUtils {

    /**
     * Return Base64-encode string.
     *
     * @param input The input.
     * @return Base64-encode string
     */
    public static String base64Encode2String(final byte[] input) {
        if (input == null || input.length == 0) return "";
        return Base64.encodeToString(input, Base64.NO_WRAP);
    }
}
