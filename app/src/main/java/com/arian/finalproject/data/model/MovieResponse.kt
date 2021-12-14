package com.arian.finalproject.data.model

import com.google.gson.annotations.SerializedName

class MovieResponse(
    @field:SerializedName("results")
    var results: List<MovieData>
)