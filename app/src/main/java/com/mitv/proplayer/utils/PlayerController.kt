package com.mitv.proplayer.utils

import android.content.Context
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.mitv.proplayer.models.Channel

class PlayerController(context: Context) {
    
    var exoPlayer: ExoPlayer? = null
    private var currentChannel: Channel? = null
    
    init {
        initializePlayer(context)
    }
    
    private fun initializePlayer(context: Context) {
        val trackSelector = DefaultTrackSelector(context).apply {
            setParameters(buildUponParameters().setMaxVideoSizeSd())
        }
        
        exoPlayer = ExoPlayer.Builder(context)
            .setTrackSelector(trackSelector)
            .build()
    }
    
    fun playChannel(channel: Channel) {
        currentChannel = channel
        
        exoPlayer?.let { player ->
            val mediaItem = MediaItem.fromUri(channel.url)
            player.setMediaItem(mediaItem)
            player.prepare()
            player.play()
        }
    }
    
    fun pause() {
        exoPlayer?.pause()
    }
    
    fun resume() {
        exoPlayer?.play()
    }
    
    fun stop() {
        exoPlayer?.stop()
    }
    
    fun release() {
        exoPlayer?.release()
        exoPlayer = null
    }
    
    fun isPlaying(): Boolean {
        return exoPlayer?.isPlaying ?: false
    }
    
    fun getCurrentChannel(): Channel? {
        return currentChannel
    }
}
