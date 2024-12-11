package com.example.kotlin6.view

import com.example.kotlin6.model.Game

interface GameEditView {
    fun showGameDetails(game: Game)
    fun navigateBack()
    fun returnUpdatedGame(game: Game, position: Int)
}
