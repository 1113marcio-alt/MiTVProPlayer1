package com.mitv.proplayer.models

data class EPGProgram(
    val channelId: String,
    val title: String,
    val description: String? = null,
    val startTime: Long,
    val endTime: Long,
    val isCurrent: Boolean = false,
    val isFavorite: Boolean = false
)
