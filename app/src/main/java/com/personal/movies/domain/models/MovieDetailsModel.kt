package com.personal.movies.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class MovieDetailsModel(
    val imageUrl: String? = null,
    var genres: String? = null,
    val description: String? = null
) : Parcelable