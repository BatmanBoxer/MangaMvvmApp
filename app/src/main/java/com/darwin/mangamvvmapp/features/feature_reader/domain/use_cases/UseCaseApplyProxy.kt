package com.darwin.mangamvvmapp.features.feature_reader.domain.use_cases

import com.darwin.mangamvvmapp.features.feature_favourites.domain.repository.FavouritesRepository
import javax.inject.Inject

class UseCaseApplyProxy @Inject constructor() {
    operator fun invoke(url: String): String {
        return if (url.contains("mkklcdn")) {
            "https://proxy-server-for-manga-api.vercel.app/?url=$url"
        } else {
            url
        }
    }
}