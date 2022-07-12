package com.personal.movies.data.entities

import com.google.gson.annotations.SerializedName

data class GenresListEntity(@SerializedName("genres") val genres: List<GenreEntity>)