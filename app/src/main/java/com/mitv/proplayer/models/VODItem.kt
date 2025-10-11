package com.mitv.proplayer.models

data class VODItem(
    val id: String,
    val name: String,
    val description: String? = null,
    val poster: String? = null,
    val rating: String? = null,
    val year: String? = null,
    val duration: String? = null,
    val streamUrl: String,
    val category: String? = null
)
