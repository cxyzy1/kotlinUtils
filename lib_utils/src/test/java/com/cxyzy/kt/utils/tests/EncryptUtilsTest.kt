package com.cxyzy.kt.utils.tests

import com.cxyzy.kt.utils.BaseTest
import com.cxyzy.utils.EncryptUtils
import org.junit.Assert.assertEquals
import org.junit.Test


class EncryptUtilsTest : BaseTest() {

    private val plainText = "3143515154353415"
    private val expectEncryptedStr = "vKp8SfrIXRu9IIBdD6HDpUsaf2V167uWTFfFJn5k3VQ="
    private val password = "654321"

    @Test
    fun testEncrypt() {
        val encryptedStr = EncryptUtils.encrypt(password, plainText)
        assertEquals(expectEncryptedStr, encryptedStr)
    }

    @Test
    fun testDecrypt() {
        val decryptedStr = EncryptUtils.decrypt(password, expectEncryptedStr)
        assertEquals(plainText, decryptedStr)
    }

}