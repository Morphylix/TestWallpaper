package com.morphylix.android.testwallpaper.domain.model.domain

/* To be honest Image should be a network entity too but I've decided to simplify solution
*  and avoid using EntityMappers because for this app it would be an overengineering in my opinion
* */

data class Image(
    val id: Int,
    val previewURL: String,
    val largeImageURL: String
)
