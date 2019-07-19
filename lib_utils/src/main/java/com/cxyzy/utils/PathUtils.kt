package com.cxyzy.utils

import android.os.Build
import android.os.Environment

import com.cxyzy.utils.internals.Utils

import java.io.File

/**
 * path utils
 */
object PathUtils {

    /**
     * Return the path of /system.
     *
     * @return the path of /system
     */
    val rootPath: String
        get() = getAbsolutePath(Environment.getRootDirectory())

    /**
     * Return the path of /data.
     *
     * @return the path of /data
     */
    val dataPath: String
        get() = getAbsolutePath(Environment.getDataDirectory())

    /**
     * Return the path of /cache.
     *
     * @return the path of /cache
     */
    val downloadCachePath: String
        get() = getAbsolutePath(Environment.getDownloadCacheDirectory())

    /**
     * Return the path of /data/data/package.
     *
     * @return the path of /data/data/package
     */
    val internalAppDataPath: String
        get() = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            Utils.getApp().applicationInfo.dataDir
        } else getAbsolutePath(Utils.getApp().dataDir)

    /**
     * Return the path of /data/data/package/code_cache.
     *
     * @return the path of /data/data/package/code_cache
     */
    val internalAppCodeCacheDir: String
        get() = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Utils.getApp().applicationInfo.dataDir + "/code_cache"
        } else getAbsolutePath(Utils.getApp().codeCacheDir)

    /**
     * Return the path of /data/data/package/cache.
     *
     * @return the path of /data/data/package/cache
     */
    val internalAppCachePath: String
        get() = getAbsolutePath(Utils.getApp().cacheDir)

    /**
     * Return the path of /data/data/package/databases.
     *
     * @return the path of /data/data/package/databases
     */
    val internalAppDbsPath: String
        get() = Utils.getApp().applicationInfo.dataDir + "/databases"

    /**
     * Return the path of /data/data/package/files.
     *
     * @return the path of /data/data/package/files
     */
    val internalAppFilesPath: String
        get() = getAbsolutePath(Utils.getApp().filesDir)

    /**
     * Return the path of /data/data/package/shared_prefs.
     *
     * @return the path of /data/data/package/shared_prefs
     */
    val internalAppSpPath: String
        get() = Utils.getApp().applicationInfo.dataDir + "/shared_prefs"

    /**
     * Return the path of /data/data/package/no_backup.
     *
     * @return the path of /data/data/package/no_backup
     */
    val internalAppNoBackupFilesPath: String
        get() = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Utils.getApp().applicationInfo.dataDir + "/no_backup"
        } else getAbsolutePath(Utils.getApp().noBackupFilesDir)

    /**
     * Return the path of /storage/emulated/0.
     *
     * @return the path of /storage/emulated/0
     */
    val externalStoragePath: String
        get() = if (isExternalStorageDisable) "" else getAbsolutePath(Environment.getExternalStorageDirectory())

    /**
     * Return the path of /storage/emulated/0/Music.
     *
     * @return the path of /storage/emulated/0/Music
     */
    val externalMusicPath: String
        get() = if (isExternalStorageDisable) "" else getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC))

    /**
     * Return the path of /storage/emulated/0/Podcasts.
     *
     * @return the path of /storage/emulated/0/Podcasts
     */
    val externalPodcastsPath: String
        get() = if (isExternalStorageDisable) "" else getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PODCASTS))

    /**
     * Return the path of /storage/emulated/0/Ringtones.
     *
     * @return the path of /storage/emulated/0/Ringtones
     */
    val externalRingtonesPath: String
        get() = if (isExternalStorageDisable) "" else getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES))

    /**
     * Return the path of /storage/emulated/0/Alarms.
     *
     * @return the path of /storage/emulated/0/Alarms
     */
    val externalAlarmsPath: String
        get() = if (isExternalStorageDisable) "" else getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS))

    /**
     * Return the path of /storage/emulated/0/Notifications.
     *
     * @return the path of /storage/emulated/0/Notifications
     */
    val externalNotificationsPath: String
        get() = if (isExternalStorageDisable) "" else getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_NOTIFICATIONS))

    /**
     * Return the path of /storage/emulated/0/Pictures.
     *
     * @return the path of /storage/emulated/0/Pictures
     */
    val externalPicturesPath: String
        get() = if (isExternalStorageDisable) "" else getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES))

    /**
     * Return the path of /storage/emulated/0/Movies.
     *
     * @return the path of /storage/emulated/0/Movies
     */
    val externalMoviesPath: String
        get() = if (isExternalStorageDisable) "" else getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES))

    /**
     * Return the path of /storage/emulated/0/Download.
     *
     * @return the path of /storage/emulated/0/Download
     */
    val externalDownloadsPath: String
        get() = if (isExternalStorageDisable) "" else getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS))

    /**
     * Return the path of /storage/emulated/0/DCIM.
     *
     * @return the path of /storage/emulated/0/DCIM
     */
    val externalDcimPath: String
        get() = if (isExternalStorageDisable) "" else getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM))

    /**
     * Return the path of /storage/emulated/0/Documents.
     *
     * @return the path of /storage/emulated/0/Documents
     */
    val externalDocumentsPath: String
        get() {
            if (isExternalStorageDisable) return ""
            return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                getAbsolutePath(Environment.getExternalStorageDirectory()) + "/Documents"
            } else getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS))
        }

    /**
     * Return the path of /storage/emulated/0/Android/data/package.
     *
     * @return the path of /storage/emulated/0/Android/data/package
     */
    val externalAppDataPath: String
        get() {
            if (isExternalStorageDisable) return ""
            val externalCacheDir = Utils.getApp().externalCacheDir ?: return ""
            return getAbsolutePath(externalCacheDir.parentFile)
        }

    /**
     * Return the path of /storage/emulated/0/Android/data/package/cache.
     *
     * @return the path of /storage/emulated/0/Android/data/package/cache
     */
    val externalAppCachePath: String
        get() = if (isExternalStorageDisable) "" else getAbsolutePath(Utils.getApp().externalCacheDir)

    /**
     * Return the path of /storage/emulated/0/Android/data/package/files.
     *
     * @return the path of /storage/emulated/0/Android/data/package/files
     */
    val externalAppFilesPath: String
        get() = if (isExternalStorageDisable) "" else getAbsolutePath(Utils.getApp().getExternalFilesDir(null))

    /**
     * Return the path of /storage/emulated/0/Android/data/package/files/Music.
     *
     * @return the path of /storage/emulated/0/Android/data/package/files/Music
     */
    val externalAppMusicPath: String
        get() = if (isExternalStorageDisable) "" else getAbsolutePath(Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_MUSIC))

    /**
     * Return the path of /storage/emulated/0/Android/data/package/files/Podcasts.
     *
     * @return the path of /storage/emulated/0/Android/data/package/files/Podcasts
     */
    val externalAppPodcastsPath: String
        get() = if (isExternalStorageDisable) "" else getAbsolutePath(Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_PODCASTS))

    /**
     * Return the path of /storage/emulated/0/Android/data/package/files/Ringtones.
     *
     * @return the path of /storage/emulated/0/Android/data/package/files/Ringtones
     */
    val externalAppRingtonesPath: String
        get() = if (isExternalStorageDisable) "" else getAbsolutePath(Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_RINGTONES))

    /**
     * Return the path of /storage/emulated/0/Android/data/package/files/Alarms.
     *
     * @return the path of /storage/emulated/0/Android/data/package/files/Alarms
     */
    val externalAppAlarmsPath: String
        get() = if (isExternalStorageDisable) "" else getAbsolutePath(Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_ALARMS))

    /**
     * Return the path of /storage/emulated/0/Android/data/package/files/Notifications.
     *
     * @return the path of /storage/emulated/0/Android/data/package/files/Notifications
     */
    val externalAppNotificationsPath: String
        get() = if (isExternalStorageDisable) "" else getAbsolutePath(Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_NOTIFICATIONS))

    /**
     * Return the path of /storage/emulated/0/Android/data/package/files/Pictures.
     *
     * @return path of /storage/emulated/0/Android/data/package/files/Pictures
     */
    val externalAppPicturesPath: String
        get() = if (isExternalStorageDisable) "" else getAbsolutePath(Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_PICTURES))

    /**
     * Return the path of /storage/emulated/0/Android/data/package/files/Movies.
     *
     * @return the path of /storage/emulated/0/Android/data/package/files/Movies
     */
    val externalAppMoviesPath: String
        get() = if (isExternalStorageDisable) "" else getAbsolutePath(Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_MOVIES))

    /**
     * Return the path of /storage/emulated/0/Android/data/package/files/Download.
     *
     * @return the path of /storage/emulated/0/Android/data/package/files/Download
     */
    val externalAppDownloadPath: String
        get() = if (isExternalStorageDisable) "" else getAbsolutePath(Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS))

    /**
     * Return the path of /storage/emulated/0/Android/data/package/files/DCIM.
     *
     * @return the path of /storage/emulated/0/Android/data/package/files/DCIM
     */
    val externalAppDcimPath: String
        get() = if (isExternalStorageDisable) "" else getAbsolutePath(Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_DCIM))

    /**
     * Return the path of /storage/emulated/0/Android/data/package/files/Documents.
     *
     * @return the path of /storage/emulated/0/Android/data/package/files/Documents
     */
    val externalAppDocumentsPath: String
        get() {
            if (isExternalStorageDisable) return ""
            return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                getAbsolutePath(Utils.getApp().getExternalFilesDir(null)) + "/Documents"
            } else getAbsolutePath(Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS))
        }

    /**
     * Return the path of /storage/emulated/0/Android/obb/package.
     *
     * @return the path of /storage/emulated/0/Android/obb/package
     */
    val externalAppObbPath: String
        get() = if (isExternalStorageDisable) "" else getAbsolutePath(Utils.getApp().obbDir)

    private val isExternalStorageDisable: Boolean
        get() = Environment.MEDIA_MOUNTED != Environment.getExternalStorageState()

    /**
     * Return the path of /data/data/package/databases/name.
     *
     * @param name The name of database.
     * @return the path of /data/data/package/databases/name
     */
    fun getInternalAppDbPath(name: String): String {
        return getAbsolutePath(Utils.getApp().getDatabasePath(name))
    }

    private fun getAbsolutePath(file: File?): String {
        return if (file == null) "" else file.absolutePath
    }
}
