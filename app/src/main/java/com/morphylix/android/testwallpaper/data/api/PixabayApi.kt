package com.morphylix.android.testwallpaper.data.api

import com.morphylix.android.testwallpaper.domain.model.network.PicHitsNetworkEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET("?key=33106230-b104905cd7ff74ed17e2229af")
    suspend fun getImageList(@Query("category") category: String, @Query("per_page") perPage: Int = 200): PicHitsNetworkEntity

}