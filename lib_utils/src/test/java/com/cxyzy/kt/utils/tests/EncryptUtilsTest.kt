package com.cxyzy.kt.utils.tests

import android.util.Base64
import com.cxyzy.kt.utils.BaseTest
import com.cxyzy.kt.utils.TestConfig.PATH_ENCRYPT
import com.cxyzy.utils.EncryptUtils
import com.cxyzy.utils.others.EncodeUtils
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File
import java.security.KeyPairGenerator
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom


class EncryptUtilsTest : BaseTest() {

    private val testHmacSHA512 = "9BA1F63365A6CAF66E46348F43CDEF956015BEA997ADEB06E69007EE3FF517DF10FC5EB860DA3D43B82C2A040C931119D2DFC6D08E253742293A868CC2D82015"
    private val testHmackey = "test"


    private val dataDES = "0008DB3345AB0223"
    private val keyDES = "6801020304050607"
    private val resDES = "1F7962581118F360"
    private val bytesDataDES = hexString2Bytes(dataDES)
    private val bytesKeyDES = hexString2Bytes(keyDES)
    private val bytesResDES = hexString2Bytes(resDES)

    private val data3DES = "1111111111111111"
    private val key3DES = "111111111111111111111111111111111111111111111111"
    private val res3DES = "F40379AB9E0EC533"
    private val bytesDataDES3 = hexString2Bytes(data3DES)
    private val bytesKeyDES3 = hexString2Bytes(key3DES)
    private val bytesResDES3 = hexString2Bytes(res3DES)

    private val dataAES = "11111111111111111111111111111111"
    private val keyAES = "11111111111111111111111111111111"
    private val resAES = "E56E26F5608B8D268F2556E198A0E01B"
    private val bytesDataAES = hexString2Bytes(dataAES)
    private val bytesKeyAES = hexString2Bytes(keyAES)
    private val bytesResAES = hexString2Bytes(resAES)

    private val publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCWuAuSCrzUXC1l4ixXBeBfotUtkALrAjLM5UHiVfOFHrRJHM41HSeHVm56UZHgJlwk80R8juu1ykuhkgrilTv7H+3MpZdIunvndDElgdgk8aI2Ip4GUlemUDvCtWd3ychWEh4kYQ8CeInQvNM08imoLFldvbjWt/IkGK+BcGzamQIDAQAB"
    private val privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJa4C5IKvNRcLWXiLFcF4F+i1S2QAusCMszlQeJV84UetEkczjUdJ4dWbnpRkeAmXCTzRHyO67XKS6GSCuKVO/sf7cyll0i6e+d0MSWB2CTxojYingZSV6ZQO8K1Z3fJyFYSHiRhDwJ4idC80zTyKagsWV29uNa38iQYr4FwbNqZAgMBAAECgYAxV1k6W1eMMg0OsKeRabQVuwoNG3tJEnQtDdSu0zKg3vdohAyh6MR7EvmiA7g86HH8CsPd/y/9WJe/8j6sBO0Ye9gt7eyQ2NiwWvlTuwNmngcSTapVvVI6NEyJFMfQt9PB1EHLNAXlz8jtJUyA7C48jReQD9p/SzAP0VxG7lwyMQJBAOjE7hAZ/6fyP3DB1fG7jr9gONZcz3TUaqx6BUn4GKZnckW08ht9Xqcqft5Hthu8BbLM9ptQ0U8QZekrJwD6ya0CQQClwstZMPu8jLhsgugVwodcG1mPEOiw9Yjnmt9+WTI07Ll2uFv//hRXBnahBBnZbucUYEbUY3kqUX9b3e9TmEodAkEAybPMbxt4VDoxCy6Mi/pxChkBZ4/pHV3sSiU6bAyWn6vIc+sGWRfca5MBePA/N+1IKtY9Y/02QwL8rH5+P/URyQJAL/hdjORGFdzLimuf6pwvPBKWKncEQCHuisghIZmClBpl2duklELddAnkztg2+tvDd/wcw14+NGb9aoKhvhl2aQJAbvcgoPU+xs0CjeexH+TS2S/jKkTRpvP2CpPK/k71m13xWdE8RtMkYY1measRmlIwOfWze7ll/PGT4dxWf31FNg=="
    private val dataRSA = "BlankjBlankjBlankjBlankjBlankjBlankjBlankjBlankjBlankjBlankjBlankjBlankjBlankjBlankjBlankjBlankjBlankjBlankjBlankjBla12345678"

    @Test
    fun encryptMD5() {
        val md5Str = "098F6BCD4621D373CADE4E832627B4F6"
        assertEquals(
                md5Str,
                EncryptUtils.encryptMD5ToString("test")
        )
        assertEquals(
                md5Str,
                EncryptUtils.encryptMD5ToString("test".toByteArray())
        )
        assertArrayEquals(
                hexString2Bytes(md5Str),
                EncryptUtils.encryptMD5("test".toByteArray())
        )
    }

    @Test
    fun encryptMD5File() {
        val fileMd5 = "7f138a09169b250e9dcb378140907378"
        assertEquals(
                fileMd5.toUpperCase(),
                EncryptUtils.encryptMD5File2String(File(PATH_ENCRYPT + "MD5.txt"))
        )
    }

    @Test
    fun encryptSHA1() {
        val sha1Str = "A94A8FE5CCB19BA61C4C0873D391E987982FBBD3"
        assertEquals(
                sha1Str,
                EncryptUtils.encryptSHA1ToString("test")
        )
        assertEquals(
                sha1Str,
                EncryptUtils.encryptSHA1ToString("test".toByteArray())
        )
        assertArrayEquals(
                hexString2Bytes(sha1Str),
                EncryptUtils.encryptSHA1("test".toByteArray())
        )
    }

    @Test
    fun encryptSHA256() {
        val sha256Str = "9F86D081884C7D659A2FEAA0C55AD015A3BF4F1B2B0B822CD15D6C15B0F00A08"
        assertEquals(
                sha256Str,
                EncryptUtils.encryptSHA256ToString("test")
        )
        assertEquals(
                sha256Str,
                EncryptUtils.encryptSHA256ToString("test".toByteArray())
        )
        assertArrayEquals(
                hexString2Bytes(sha256Str),
                EncryptUtils.encryptSHA256("test".toByteArray()))

    }

    @Test
    fun encryptSHA512() {
        val sha512Str = "EE26B0DD4AF7E749AA1A8EE3C10AE9923F618980772E473F8819A5D4940E0DB27AC185F8A0E1D5F84F88BC887FD67B143732C304CC5FA9AD8E6F57F50028A8FF"
        assertEquals(
                sha512Str,
                EncryptUtils.encryptSHA512ToString("test")
        )
        assertEquals(
                sha512Str,
                EncryptUtils.encryptSHA512ToString("test".toByteArray())
        )
        assertArrayEquals(
                hexString2Bytes(sha512Str),
                EncryptUtils.encryptSHA512("test".toByteArray())
        )
    }

    @Test
    fun encryptHmacMD5() {
        val hmacStr = "CD4B0DCBE0F4538B979FB73664F51ABE"
        assertEquals(
                hmacStr,
                EncryptUtils.encryptHmacMD5ToString("test", testHmackey)
        )
        assertEquals(
                hmacStr,
                EncryptUtils.encryptHmacMD5ToString("test".toByteArray(), testHmackey.toByteArray())
        )
        assertArrayEquals(
                hexString2Bytes(hmacStr),
                EncryptUtils.encryptHmacMD5("test".toByteArray(), testHmackey.toByteArray())
        )
    }

    @Test
    fun encryptHmacSHA1() {
        val sha1Str = "0C94515C15E5095B8A87A50BA0DF3BF38ED05FE6"
        assertEquals(
                sha1Str,
                EncryptUtils.encryptHmacSHA1ToString("test", testHmackey)
        )
        assertEquals(
                sha1Str,
                EncryptUtils.encryptHmacSHA1ToString("test".toByteArray(), testHmackey.toByteArray())
        )
        assertArrayEquals(
                hexString2Bytes(sha1Str),
                EncryptUtils.encryptHmacSHA1("test".toByteArray(), testHmackey.toByteArray())
        )
    }

    @Test
    fun encryptHmacSHA256() {
        val sha256Str = "88CD2108B5347D973CF39CDF9053D7DD42704876D8C9A9BD8E2D168259D3DDF7"
        assertEquals(
                sha256Str,
                EncryptUtils.encryptHmacSHA256ToString("test", testHmackey)
        )
        assertEquals(
                sha256Str,
                EncryptUtils.encryptHmacSHA256ToString("test".toByteArray(), testHmackey.toByteArray())
        )
        assertArrayEquals(
                hexString2Bytes(sha256Str),
                EncryptUtils.encryptHmacSHA256("test".toByteArray(), testHmackey.toByteArray())
        )
    }

    @Test
    fun encryptHmacSHA512() {
        assertEquals(
                testHmacSHA512,
                EncryptUtils.encryptHmacSHA512ToString("test", testHmackey)
        )
        assertEquals(
                testHmacSHA512,
                EncryptUtils.encryptHmacSHA512ToString("test".toByteArray(), testHmackey.toByteArray())
        )
        assertArrayEquals(
                hexString2Bytes(testHmacSHA512),
                EncryptUtils.encryptHmacSHA512("test".toByteArray(), testHmackey.toByteArray())
        )
    }

    @Test
    fun encryptDES() {
        assertArrayEquals(
                bytesResDES,
                EncryptUtils.encryptDES(bytesDataDES!!, bytesKeyDES!!, "DES/ECB/NoPadding", null)
        )
        assertEquals(
                resDES,
                EncryptUtils.encryptDES2HexString(bytesDataDES, bytesKeyDES, "DES/ECB/NoPadding", null)
        )
        assertArrayEquals(
                base64Encode(bytesResDES),
                EncryptUtils.encryptDES2Base64(bytesDataDES, bytesKeyDES, "DES/ECB/NoPadding", null)
        )
    }

    @Test
    fun decryptDES() {
        assertArrayEquals(
                bytesDataDES,
                EncryptUtils.decryptDES(bytesResDES, bytesKeyDES!!, "DES/ECB/NoPadding", null)
        )
        assertArrayEquals(
                bytesDataDES,
                EncryptUtils.decryptHexStringDES(resDES, bytesKeyDES, "DES/ECB/NoPadding", null)
        )
        assertArrayEquals(
                bytesDataDES,
                EncryptUtils.decryptBase64DES(base64Encode(bytesResDES), bytesKeyDES, "DES/ECB/NoPadding", null)
        )
    }

    @Test
    fun encrypt3DES() {
        assertArrayEquals(
                bytesResDES3,
                EncryptUtils.encrypt3DES(bytesDataDES3!!, bytesKeyDES3!!, "DESede/ECB/NoPadding", null)
        )
        assertEquals(
                res3DES,
                EncryptUtils.encrypt3DES2HexString(bytesDataDES3, bytesKeyDES3, "DESede/ECB/NoPadding", null)
        )
        assertArrayEquals(
                base64Encode(bytesResDES3),
                EncryptUtils.encrypt3DES2Base64(bytesDataDES3, bytesKeyDES3, "DESede/ECB/NoPadding", null)
        )
    }

    @Test
    fun decrypt3DES() {
        assertArrayEquals(
                bytesDataDES3,
                EncryptUtils.decrypt3DES(bytesResDES3, bytesKeyDES3!!, "DESede/ECB/NoPadding", null)
        )
        assertArrayEquals(
                bytesDataDES3,
                EncryptUtils.decryptHexString3DES(res3DES, bytesKeyDES3, "DESede/ECB/NoPadding", null)
        )
        assertArrayEquals(
                bytesDataDES3,
                EncryptUtils.decryptBase64_3DES(base64Encode(bytesResDES3), bytesKeyDES3, "DESede/ECB/NoPadding", null)
        )
    }

    @Test
    fun encryptAES() {
        assertArrayEquals(
                bytesResAES,
                EncryptUtils.encryptAES(bytesDataAES!!, bytesKeyAES!!, "AES/ECB/NoPadding", null)
        )
        assertEquals(
                resAES,
                EncryptUtils.encryptAES2HexString(bytesDataAES, bytesKeyAES, "AES/ECB/NoPadding", null)
        )
        assertArrayEquals(
                base64Encode(bytesResAES),
                EncryptUtils.encryptAES2Base64(bytesDataAES, bytesKeyAES, "AES/ECB/NoPadding", null)
        )
    }

    @Test
    fun decryptAES() {
        assertArrayEquals(
                bytesDataAES,
                EncryptUtils.decryptAES(bytesResAES, bytesKeyAES!!, "AES/ECB/NoPadding", null)
        )
        assertArrayEquals(
                bytesDataAES,
                EncryptUtils.decryptHexStringAES(resAES, bytesKeyAES, "AES/ECB/NoPadding", null)
        )
        assertArrayEquals(bytesDataAES,
                EncryptUtils.decryptBase64AES(base64Encode(bytesResAES), bytesKeyAES, "AES/ECB/NoPadding", null)
        )
    }

    @Test
    fun encryptDecryptRSA() {
        try {
            genKeyPair()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        assertArrayEquals(EncryptUtils.decryptRSA(
                EncryptUtils.encryptRSA(
                        dataRSA.toByteArray(),
                        base64Decode(publicKey.toByteArray()),
                        true,
                        "RSA/ECB/PKCS1Padding"
                ),
                base64Decode(privateKey.toByteArray()),
                false,
                "RSA/ECB/PKCS1Padding"
        ), dataRSA.toByteArray())
    }

    private fun base64Encode(input: ByteArray?): ByteArray {
        return Base64.encode(input, Base64.NO_WRAP)
    }

    private fun base64Decode(input: ByteArray): ByteArray {
        return Base64.decode(input, Base64.NO_WRAP)
    }

    private fun hexString2Bytes(hexString: String): ByteArray? {
        var hexString = hexString
        if (isSpace(hexString)) return null
        var len = hexString.length
        if (len % 2 != 0) {
            hexString = "0$hexString"
            len = len + 1
        }
        val hexBytes = hexString.toUpperCase().toCharArray()
        val ret = ByteArray(len shr 1)
        var i = 0
        while (i < len) {
            ret[i shr 1] = (hex2Int(hexBytes[i]) shl 4 or hex2Int(hexBytes[i + 1])).toByte()
            i += 2
        }
        return ret
    }

    private fun isSpace(s: String?): Boolean {
        if (s == null) return true
        var i = 0
        val len = s.length
        while (i < len) {
            if (!Character.isWhitespace(s[i])) {
                return false
            }
            ++i
        }
        return true
    }

    private fun hex2Int(hexChar: Char): Int {
        return if (hexChar >= '0' && hexChar <= '9') {
            hexChar - '0'
        } else if (hexChar >= 'A' && hexChar <= 'F') {
            hexChar - 'A' + 10
        } else {
            throw IllegalArgumentException()
        }
    }

    @Throws(NoSuchAlgorithmException::class)
    private fun genKeyPair() {

        val secureRandom = SecureRandom()

        val keyPairGenerator = KeyPairGenerator.getInstance("RSA")

        keyPairGenerator.initialize(1024, secureRandom)

        val keyPair = keyPairGenerator.generateKeyPair()

        val publicKey = keyPair.public

        val privateKey = keyPair.private

        val publicKeyBytes = publicKey.encoded
        val privateKeyBytes = privateKey.encoded

        val publicKeyBase64 = EncodeUtils.base64Encode2String(publicKeyBytes)
        val privateKeyBase64 = EncodeUtils.base64Encode2String(privateKeyBytes)

        println("publicKeyBase64.length():" + publicKeyBase64.length)
        println("publicKeyBase64:$publicKeyBase64")

        println("privateKeyBase64.length():" + privateKeyBase64.length)
        println("privateKeyBase64:$privateKeyBase64")
    }
}