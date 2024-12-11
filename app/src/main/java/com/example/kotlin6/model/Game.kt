package com.example.kotlin6.model

import java.io.Serializable

data class Game(
    var title: String,
    var description: String,
    var genre: String,
    var year: Int
) : Serializable