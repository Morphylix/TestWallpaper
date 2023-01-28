package com.morphylix.android.testwallpaper.domain.model.network

import com.morphylix.android.testwallpaper.domain.model.domain.Image

/* To be honest Image should be a network entity too but I've decided to simplify solution
*  and avoid using EntityMappers because for this app it would be an overengineering in my opinion
* */

data class PicHitsNetworkEntity(
    val hits: List<Image>
)
