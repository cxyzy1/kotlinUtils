package com.cxyzy.basic_libs

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri


/**
 * 播放URL对应文件
 */
private fun playFromUrl(context: Context, audioUrl: String) {
    val uri = Uri.parse(audioUrl)
    val mediaPlayer = MediaPlayer.create(context, uri)
    mediaPlayer.start()
}

/**
 * 播放raw目录下文件
 */
//private fun playFromRaw(context: Context) {
//    val mediaPlayer = MediaPlayer.create(context, R.raw.test)
//    mediaPlayer.start()
//}

/**
 * 播放assets目录下文件
 */
private fun playFromAssets(context: Context, fileName: String) {
    val fd = context.assets.openFd(fileName)
    val mediaPlayer = MediaPlayer()
    mediaPlayer.setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)
    mediaPlayer.prepare()
    mediaPlayer.start()
}

/**
 * 播放SD卡上文件
 * 需要动态申请SD读权限
 */
private fun playFromSdCard(context: Context, filePath: String) {
    val mediaPlayer = MediaPlayer()
    mediaPlayer.setDataSource(filePath)
    mediaPlayer.prepare()
    mediaPlayer.start()
}


