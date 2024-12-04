package com.example.kotlin6.data

import java.io.Serializable

data class Game(
    var title: String,
    var description: String,
    var genre: String,
    var year: Int
) : Serializable