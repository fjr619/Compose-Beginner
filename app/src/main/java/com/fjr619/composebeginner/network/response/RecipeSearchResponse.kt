package com.fjr619.composebeginner.network.response

import com.fjr619.composebeginner.network.model.RecipeDto
import com.google.gson.annotations.SerializedName

class RecipeSearchResponse(

    @SerializedName("count")
    var count: Int,

    @SerializedName("results")
    var recipes: List<RecipeDto>,
)