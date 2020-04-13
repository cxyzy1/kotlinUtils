package com.cxyzy.basic_libs

import android.app.Activity
import android.net.Uri
import android.widget.MediaController
import android.widget.VideoView

/**
 * 播放视频
 */

fun Activity.playVideo(rawFileId: Int, videoView: VideoView) = playVideo("android.resource://$packageName/$rawFileId", videoView)

fun Activity.playVideo(filePath: String, videoView: VideoView) {
    val mediaController = MediaController(this)
    videoView.setMediaController(mediaController)
    videoView.setVideoURI(Uri.parse(filePath))
    videoView.start()
}

