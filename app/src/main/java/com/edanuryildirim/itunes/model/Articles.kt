package com.edanuryildirim.itunes.model

import java.io.Serializable


data class Articles (
    var artistName: String,
    var collectionName: String,
    val kind: String,
    val collectionCensoredName: String,
    val artistViewUrl: String,
    val wrapperType: String,
    val primaryGenreName: String,
    val artworkUrl100: String,
    val copyright: String,
    val country: String,
    val currency: String,
    val releaseDate: String,
    var collectionPrice: Int,
    var trackCount: Int
): Serializable