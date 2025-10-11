package com.mitv.proplayer.models

data class Channel(
    val id: String,
    var name: String,
    var url: String,
    val logo: String? = null,
    val group: String? = null,
    val isFavorite: Boolean = false
) {
    // Construtor vazio para Firebase etc.
    constructor() : this("", "", "", null, null, false)
}
