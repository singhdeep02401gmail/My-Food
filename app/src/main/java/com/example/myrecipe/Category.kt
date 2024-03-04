package com.example.myrecipe

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Category(val idCategory: Int,
                    val strCategory: String,
                    val strCategoryThumb: String,   // image
                    val strCategoryDescription: String): Parcelable


data class CategoriesResponse(val categories: List<Category>)
