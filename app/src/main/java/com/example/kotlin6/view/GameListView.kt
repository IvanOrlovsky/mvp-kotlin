package com.example.kotlin6.view

import com.example.kotlin6.data.Game

interface GameListView {
    fun showGames(games: List<Game>)
}
