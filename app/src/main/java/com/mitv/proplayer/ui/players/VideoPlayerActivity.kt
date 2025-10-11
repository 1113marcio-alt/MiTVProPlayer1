package com.mitv.proplayer.ui.players

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.mitv.proplayer.R

class VideoPlayerActivity : AppCompatActivity() {
    
    private var exoPlayer: ExoPlayer? = null
    private lateinit var playerView: StyledPlayerView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)
        
        setupPlayer()
    }
    
    private fun setupPlayer() {
        playerView = findViewById(R.id.player_view)
        
        // Criar o player ExoPlayer (vamos configurar depois)
        exoPlayer = ExoPlayer.Builder(this).build()
        playerView.player = exoPlayer
        
        // URL exemplo - depois vamos conectar com os canais reais
        val videoUrl = "https://example.com/stream.m3u8"
        
        val mediaItem = MediaItem.fromUri(Uri.parse(videoUrl))
        exoPlayer?.setMediaItem(mediaItem)
        exoPlayer?.prepare()
    }
    
    override fun onDestroy() {
        super.onDestroy()
        exoPlayer?.release()
        exoPlayer = null
    }
}
