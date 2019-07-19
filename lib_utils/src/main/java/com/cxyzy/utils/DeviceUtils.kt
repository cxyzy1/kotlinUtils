package com.cxyzy.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Debug
import android.provider.Settings
import android.telephony.TelephonyManager
import android.text.TextUtils

import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission

import com.cxyzy.utils.internals.ShellUtils
import com.cxyzy.utils.internals.Utils

import java.io.File
import java.net.InetAddress
import java.net.NetworkInterface
import java.net.SocketException

import android.Manifest.permission.ACCESS_WIFI_STATE
import android.Manifest.permission.INTERNET

/**
 * device utils
 */
object DeviceUtils {

    /**
     * Return whether device is rooted.
     *
     * @return `true`: yes<br></br>`false`: no
     */
    val isDeviceRooted: Boolean
        get() {
            val su = "su"
            val locations = arrayOf("/system/bin/", "/system/xbin/", "/sbin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/xbin/", "/data/local/bin/", "/data/local/", "/system/sbin/", "/usr/bin/", "/vendor/bin/")
            for (location in locations) {
                if (File(location + su).exists()) {
                    return true
                }
            }
            return false
        }

    /**
     * Return whether ADB is enabled.
     *
     * @return `true`: yes<br></br>`false`: no
     */
    val isAdbEnabled: Boolean
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        get() = Settings.Secure.getInt(
                Utils.app.contentResolver,
                Settings.Global.ADB_ENABLED, 0
        ) > 0

    /**
     * Return the version name of device's system.
     *
     * @return the version name of device's system
     */
    val sdkVersionName: String
        get() = android.os.Build.VERSION.RELEASE

    /**
     * Return version code of device's system.
     *
     * @return version code of device's system
     */
    val sdkVersionCode: Int
        get() = android.os.Build.VERSION.SDK_INT

    /**
     * Return the android id of device.
     *
     * @return the android id of device
     */
    val androidID: String
        @SuppressLint("HardwareIds")
        get() {
            val id = Settings.Secure.getString(
                    Utils.app.contentResolver,
                    Settings.Secure.ANDROID_ID
            )
            return id ?: ""
        }

    /**
     * Return the MAC address.
     *
     * Must hold `<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />`,
     * `<uses-permission android:name="android.permission.INTERNET" />`
     *
     * @return the MAC address
     */
    val macAddress: String
        @RequiresPermission(allOf = [ACCESS_WIFI_STATE, INTERNET])
        get() = getMacAddress()

    private val macAddressByWifiInfo: String
        @SuppressLint("MissingPermission", "HardwareIds")
        get() {
            try {
                val wifi = Utils.app
                        .applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
                if (wifi != null) {
                    val info = wifi.connectionInfo
                    if (info != null) return info.macAddress
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return "02:00:00:00:00:00"
        }

    private val macAddressByNetworkInterface: String
        get() {
            try {
                val nis = NetworkInterface.getNetworkInterfaces()
                while (nis.hasMoreElements()) {
                    val ni = nis.nextElement()
                    if (ni == null || !ni.name.equals("wlan0", ignoreCase = true)) {
                        continue
                    }
                    val macBytes = ni.hardwareAddress
                    if (macBytes != null && macBytes.size > 0) {
                        val sb = StringBuilder()
                        for (b in macBytes) {
                            sb.append(String.format("%02x:", b))
                        }
                        return sb.substring(0, sb.length - 1)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return "02:00:00:00:00:00"
        }

    private val macAddressByInetAddress: String
        get() {
            try {
                val inetAddress = inetAddress
                if (inetAddress != null) {
                    val ni = NetworkInterface.getByInetAddress(inetAddress)
                    if (ni != null) {
                        val macBytes = ni.hardwareAddress
                        if (macBytes != null && macBytes.size > 0) {
                            val sb = StringBuilder()
                            for (b in macBytes) {
                                sb.append(String.format("%02x:", b))
                            }
                            return sb.substring(0, sb.length - 1)
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return "02:00:00:00:00:00"
        }

    private// To prevent phone of xiaomi return "10.0.2.15"
    val inetAddress: InetAddress?
        get() {
            try {
                val nis = NetworkInterface.getNetworkInterfaces()
                while (nis.hasMoreElements()) {
                    val ni = nis.nextElement()
                    if (!ni.isUp) continue
                    val addresses = ni.inetAddresses
                    while (addresses.hasMoreElements()) {
                        val inetAddress = addresses.nextElement()
                        if (!inetAddress.isLoopbackAddress) {
                            val hostAddress = inetAddress.hostAddress
                            if (hostAddress.indexOf(':') < 0) return inetAddress
                        }
                    }
                }
            } catch (e: SocketException) {
                e.printStackTrace()
            }

            return null
        }

    private val macAddressByFile: String
        get() {
            var result: ShellUtils.CommandResult = ShellUtils.execCmd("getprop wifi.interface", false)
            if (result.result == 0) {
                val name = result.successMsg
                if (name != null) {
                    result = ShellUtils.execCmd("cat /sys/class/net/$name/address", false)
                    if (result.result == 0) {
                        val address = result.successMsg
                        if (address != null && address.length > 0) {
                            return address
                        }
                    }
                }
            }
            return "02:00:00:00:00:00"
        }

    /**
     * Return the manufacturer of the product/hardware.
     *
     * e.g. Xiaomi
     *
     * @return the manufacturer of the product/hardware
     */
    val manufacturer: String
        get() = Build.MANUFACTURER

    /**
     * Return the model of device.
     *
     * e.g. MI2SC
     *
     * @return the model of device
     */
    val model: String
        get() {
            var model: String? = Build.MODEL
            if (model != null) {
                model = model.trim { it <= ' ' }.replace("\\s*".toRegex(), "")
            } else {
                model = ""
            }
            return model
        }

    /**
     * Return an ordered list of ABIs supported by this device. The most preferred ABI is the first
     * element in the list.
     *
     * @return an ordered list of ABIs supported by this device
     */
    val abIs: Array<String>
        get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Build.SUPPORTED_ABIS
        } else {
            if (!TextUtils.isEmpty(Build.CPU_ABI2)) {
                arrayOf(Build.CPU_ABI, Build.CPU_ABI2)
            } else arrayOf(Build.CPU_ABI)
        }

    /**
     * Return whether device is tablet.
     *
     * @return `true`: yes<br></br>`false`: no
     */
    val isTablet: Boolean
        get() = Utils.app.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_LARGE

    /**
     * Return whether device is emulator.
     *
     * @return `true`: yes<br></br>`false`: no
     */
    val isEmulator: Boolean
        get() {
            val checkProperty = (Build.FINGERPRINT.startsWith("generic")
                    || Build.FINGERPRINT.toLowerCase().contains("vbox")
                    || Build.FINGERPRINT.toLowerCase().contains("test-keys")
                    || Build.MODEL.contains("google_sdk")
                    || Build.MODEL.contains("Emulator")
                    || Build.SERIAL.equals("unknown", ignoreCase = true)
                    || Build.SERIAL.equals("android", ignoreCase = true)
                    || Build.MODEL.contains("Android SDK built for x86")
                    || Build.MANUFACTURER.contains("Genymotion")
                    || Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")
                    || "google_sdk" == Build.PRODUCT)
            if (checkProperty) return true

            val checkDebuggerConnected = Debug.isDebuggerConnected()
            if (checkDebuggerConnected) return true

            var operatorName = ""
            val tm = Utils.app.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            if (tm != null) {
                val name = tm.networkOperatorName
                if (name != null) {
                    operatorName = name
                }
            }
            val checkOperatorName = operatorName.toLowerCase() == "android"
            if (checkOperatorName) return true

            val url = "tel:" + "123456"
            val intent = Intent()
            intent.data = Uri.parse(url)
            intent.action = Intent.ACTION_DIAL
            val checkDial = intent.resolveActivity(Utils.app.packageManager) != null
            return if (checkDial) true else false

        }

    /**
     * Return the MAC address.
     *
     * Must hold `<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />`,
     * `<uses-permission android:name="android.permission.INTERNET" />`
     *
     * @return the MAC address
     */
    @RequiresPermission(allOf = [ACCESS_WIFI_STATE, INTERNET])
    fun getMacAddress(vararg excepts: String): String {
        var macAddress = macAddressByNetworkInterface
        if (isAddressNotInExcepts(macAddress, *excepts)) {
            return macAddress
        }
        macAddress = macAddressByInetAddress
        if (isAddressNotInExcepts(macAddress, *excepts)) {
            return macAddress
        }
        macAddress = macAddressByWifiInfo
        if (isAddressNotInExcepts(macAddress, *excepts)) {
            return macAddress
        }
        macAddress = macAddressByFile
        return if (isAddressNotInExcepts(macAddress, *excepts)) {
            macAddress
        } else ""
    }

    private fun isAddressNotInExcepts(address: String, vararg excepts: String): Boolean {
        if (excepts.isEmpty()) {
            return "02:00:00:00:00:00" != address
        }
        for (filter in excepts) {
            if (address == filter) {
                return false
            }
        }
        return true
    }

}