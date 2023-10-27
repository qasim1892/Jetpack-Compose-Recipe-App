package com.ALi.mvvmrecipeapp.network.response

import com.example.jetpackcomposeapp.network.model.RecipeDto
import com.google.gson.annotations.SerializedName

data class RecipeSearchResponse(

    @SerializedName("count")
        var count: Int,

    @SerializedName("results")
        var recipes: List<RecipeDto>,
)