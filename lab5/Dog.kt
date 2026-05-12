package com.example.woofapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Dog(
    @DrawableRes val imageRes: Int,
    val name: String,
    val age: Int
)