package com.example.kotlin6.view

import com.example.kotlin6.model.Game

interface GameDetailView {
    fun showGameDetails(game: Game)
    fun navigateToEditScreen(game: Game, position: Int)
}
