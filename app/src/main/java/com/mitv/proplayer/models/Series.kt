package com.mitv.proplayer.models

data class Series(
    val id: String,
    val name: String,
    val description: String? = null,
    val poster: String? = null,
    val rating: String? = null,
    val year: String? = null,
    val genre: String? = null,
    val seasons: List<Season> = emptyList()
)

data class Season(
    val number: Int,
    val episodes: List<Episode> = emptyList()
)

data class Episode(
    val id: String,
    val title: String,
    val description: String? = null,
    val duration: String? = null,
    val streamUrl: String,
    val episodeNumber: Int
)
