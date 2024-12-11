package com.example.kotlin6.view

import com.example.kotlin6.model.Game

interface GameListView {
    fun showGames(games: List<Game>)
}
