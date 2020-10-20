package com.xiaoyu.videoplayer

import android.content.res.Configuration
import android.content.res.Resources
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import kotlinx.android.synthetic.main.activity_play.*


val Float.toPx: Float
    get() = android.util.TypedValue.applyDimension(
        android.util.TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics
    )

val Int.toPx: Int
    get() = android.util.TypedValue.applyDimension(
        android.util.TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()

class PlayActivity : AppCompatActivity() {
    private var player: SimpleExoPlayer? = null
    private val mediaItems = mutableListOf<MediaItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
        initializePlayer()
    }

    private fun initializePlayer() {
        val name = intent.getStringExtra("name")
        val uri = intent.getStringExtra("uri")
        Log.i(TAG, "initializePlayer: name: $name  uir: $uri")
        SimpleExoPlayer.Builder(applicationContext).build().apply {
            player = this
            playerView.player = this
            val mediaItem: MediaItem = MediaItem.fromUri(Uri.parse(uri))
            this.setMediaItem(mediaItem)
            this.prepare()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) { //横屏
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
            //横屏 视频充满全屏
            val layoutParams =
                playerView.layoutParams
            layoutParams.height = LinearLayout.LayoutParams.MATCH_PARENT
            layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT
            playerView.layoutParams = layoutParams
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            val attrs: WindowManager.LayoutParams = window.attributes
            attrs.flags = attrs.flags and WindowManager.LayoutParams.FLAG_FULLSCREEN.inv()
            window.attributes = attrs
            window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

            //横屏 视频充满全屏
            val layoutParams =
                playerView.layoutParams
            layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT
            layoutParams.height = 200.toPx
            playerView.layoutParams = layoutParams

        }
    }

    override fun onResume() {
        super.onResume()
        player?.play()
    }

    override fun onPause() {
        super.onPause()
        player?.pause()
    }

    override fun onDestroy() {
    super.onDestroy()
    player?.release()
    player = null
    mediaItems.clear()
}


companion object {
    private const val TAG = "PlayActivity"
}
}