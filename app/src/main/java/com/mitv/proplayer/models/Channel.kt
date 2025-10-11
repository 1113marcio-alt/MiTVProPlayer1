package com.mitv.proplayer.models

data class Channel(
    val id: String,
    val name: String,
    val url: String,
    val logo: String? = null,
    val group: String? = null,
    val isFavorite: Boolean = false
)
