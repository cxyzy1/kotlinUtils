package com.cxyzy.kt.utils.tests

import android.util.Base64
import com.cxyzy.kt.utils.BaseTest
import com.cxyzy.kt.utils.TestConfig.PATH_ENCRYPT
import com.cxyzy.utils.EncryptUtils
import com.cxyzy.utils.others.EncodeUtils
import org.junit.Assert
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File
import java.security.KeyPairGenerator
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom


class EncryptUtilsTest : BaseTest() {

    private val testHmacSHA512 = "FC55AD54B95F55A8E32EA1BAD7748C157F80679F5561EC95A3EAD975316BA85363CB4AF6462D695F742F469EDC2D577272BE359A7F9E9C7018FDF4C921E1B3CF"
    private val testHmackey = "blankj"


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
        val blankjMD5 = "AAC25CD336E01C8655F4EC7875445A60"
        assertEquals(
                blankjMD5,
                EncryptUtils.encryptMD5ToString("blankj")
        )
        assertEquals(
                blankjMD5,
                EncryptUtils.encryptMD5ToString("blankj".toByteArray())
        )
        assertArrayEquals(
                hexString2Bytes(blankjMD5),
                EncryptUtils.encryptMD5("blankj".toByteArray())
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
        val blankjSHA1 = "C606ACCB1FEB669E19D080ADDDDBB8E6CDA5F43C"
        assertEquals(
                blankjSHA1,
                EncryptUtils.encryptSHA1ToString("blankj")
        )
        assertEquals(
                blankjSHA1,
                EncryptUtils.encryptSHA1ToString("blankj".toByteArray())
        )
        assertArrayEquals(
                hexString2Bytes(blankjSHA1),
                EncryptUtils.encryptSHA1("blankj".toByteArray())
        )
    }

    @Test
    fun encryptSHA224() {
        val blankjSHA224 = "F4C5C0E8CF56CAC4D06DB6B523F67621859A9D79BDA4B2AC03097D5F"
        assertEquals(
                blankjSHA224,
                EncryptUtils.encryptSHA224ToString("blankj")
        )
        assertEquals(
                blankjSHA224,
                EncryptUtils.encryptSHA224ToString("blankj".toByteArray())
        )
        assertArrayEquals(
                hexString2Bytes(blankjSHA224),
                EncryptUtils.encryptSHA224("blankj".toByteArray())
        )
    }

    @Test
    fun encryptSHA256() {
        val blankjSHA256 = "8BD80AE90DFBA112786367BEBDDEE60A638EF5B82682EDF8F3D3CA8E6BFEF648"
        assertEquals(
                blankjSHA256,
                EncryptUtils.encryptSHA256ToString("blankj")
        )
        assertEquals(
                blankjSHA256,
                EncryptUtils.encryptSHA256ToString("blankj".toByteArray())
        )
        assertArrayEquals(
                hexString2Bytes(blankjSHA256),
                EncryptUtils.encryptSHA256("blankj".toByteArray()))

    }

    @Test
    fun encryptSHA384() {
        val blankjSHA384 = "BF831E5221FC108D6A72ACB888BA3EB0C030A5F01BA2F739856BE70681D86F992B85E0D461101C74BAEDA895BD422557"
        assertEquals(
                blankjSHA384,
                EncryptUtils.encryptSHA384ToString("blankj")
        )
        assertEquals(
                blankjSHA384,
                EncryptUtils.encryptSHA384ToString("blankj".toByteArray())
        )
        assertArrayEquals(
                hexString2Bytes(blankjSHA384),
                EncryptUtils.encryptSHA384("blankj".toByteArray())
        )
    }

    @Test
    fun encryptSHA512() {
        val blankjSHA512 = "D59D31067F614ED3586F85A31FEFDB7F33096316DA26EBE0FF440B241C8560D96650F100D78C512560C976949EFA89CB5D5589DCF68C7FAADE98F03BCFEC2B45"
        assertEquals(
                blankjSHA512,
                EncryptUtils.encryptSHA512ToString("blankj")
        )
        assertEquals(
                blankjSHA512,
                EncryptUtils.encryptSHA512ToString("blankj".toByteArray())
        )
        assertArrayEquals(
                hexString2Bytes(blankjSHA512),
                EncryptUtils.encryptSHA512("blankj".toByteArray())
        )
    }

    @Test
    fun encryptHmacMD5() {
        val blankjHmacMD5 = "2BA3FDABEE222522044BEC0CE5D6B490"
        assertEquals(
                blankjHmacMD5,
                EncryptUtils.encryptHmacMD5ToString("blankj", testHmackey)
        )
        assertEquals(
                blankjHmacMD5,
                EncryptUtils.encryptHmacMD5ToString("blankj".toByteArray(), testHmackey.toByteArray())
        )
        assertArrayEquals(
                hexString2Bytes(blankjHmacMD5),
                EncryptUtils.encryptHmacMD5("blankj".toByteArray(), testHmackey.toByteArray())
        )
    }

    @Test
    fun encryptHmacSHA1() {
        val blankjHmacSHA1 = "88E83EFD915496860C83739BE2CF4752B2AC105F"
        assertEquals(
                blankjHmacSHA1,
                EncryptUtils.encryptHmacSHA1ToString("blankj", testHmackey)
        )
        assertEquals(
                blankjHmacSHA1,
                EncryptUtils.encryptHmacSHA1ToString("blankj".toByteArray(), testHmackey.toByteArray())
        )
        assertArrayEquals(
                hexString2Bytes(blankjHmacSHA1),
                EncryptUtils.encryptHmacSHA1("blankj".toByteArray(), testHmackey.toByteArray())
        )
    }

    @Test
    fun encryptHmacSHA224() {
        val blankjHmacSHA224 = "E392D83D1030323FB2E062E8165A3AD38366E53DF19EA3290961E153"
        assertEquals(
                blankjHmacSHA224,
                EncryptUtils.encryptHmacSHA224ToString("blankj", testHmackey)
        )
        assertEquals(
                blankjHmacSHA224,
                EncryptUtils.encryptHmacSHA224ToString("blankj".toByteArray(), testHmackey.toByteArray())
        )
        assertArrayEquals(
                hexString2Bytes(blankjHmacSHA224),
                EncryptUtils.encryptHmacSHA224("blankj".toByteArray(), testHmackey.toByteArray())
        )
    }

    @Test
    fun encryptHmacSHA256() {
        val blankjHmacSHA256 = "A59675F13FC9A6E06D8DC90D4DC01DB9C991B0B95749D2471E588BF311DA2C67"
        assertEquals(
                blankjHmacSHA256,
                EncryptUtils.encryptHmacSHA256ToString("blankj", testHmackey)
        )
        assertEquals(
                blankjHmacSHA256,
                EncryptUtils.encryptHmacSHA256ToString("blankj".toByteArray(), testHmackey.toByteArray())
        )
        assertArrayEquals(
                hexString2Bytes(blankjHmacSHA256),
                EncryptUtils.encryptHmacSHA256("blankj".toByteArray(), testHmackey.toByteArray())
        )
    }

    @Test
    fun encryptHmacSHA384() {
        val blankjHmacSHA384 = "9FC2F49C7EDE698EA59645B3BEFBBE67DCC7D6623E03D4D03CDA1324F7B6445BC428AB42F6A962CF79AFAD1302C3223D"
        assertEquals(
                blankjHmacSHA384,
                EncryptUtils.encryptHmacSHA384ToString("blankj", testHmackey)
        )
        assertEquals(
                blankjHmacSHA384,
                EncryptUtils.encryptHmacSHA384ToString("blankj".toByteArray(), testHmackey.toByteArray())
        )
        assertArrayEquals(
                hexString2Bytes(blankjHmacSHA384),
                EncryptUtils.encryptHmacSHA384("blankj".toByteArray(), testHmackey.toByteArray())
        )
    }

    @Test
    fun encryptHmacSHA512() {
        assertEquals(
                testHmacSHA512,
                EncryptUtils.encryptHmacSHA512ToString("blankj", testHmackey)
        )
        assertEquals(
                testHmacSHA512,
                EncryptUtils.encryptHmacSHA512ToString("blankj".toByteArray(), testHmackey.toByteArray())
        )
        assertArrayEquals(
                hexString2Bytes(testHmacSHA512),
                EncryptUtils.encryptHmacSHA512("blankj".toByteArray(), testHmackey.toByteArray())
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