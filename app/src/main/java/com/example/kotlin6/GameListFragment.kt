package com.example.kotlin6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin6.model.Game
import com.example.kotlin6.databinding.FragmentGameListBinding
import com.example.kotlin6.presenter.GameListPresenter
import com.example.kotlin6.view.GameListView
import androidx.navigation.fragment.findNavController

class GameListFragment : Fragment(), GameListView {

    private lateinit var binding: FragmentGameListBinding
    private lateinit var gamePresenter: GameListPresenter
    private lateinit var gameAdapter: GameAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameListBinding.inflate(inflater, container, false)

        gamePresenter = GameListPresenter(this, requireContext())

        gameAdapter = GameAdapter(
            mutableListOf(),
            { game, position ->
                val action = GameListFragmentDirections.actionGameListFragmentToGameDetailFragment(game, position)
                findNavController().navigate(action)
            },
            { position -> gamePresenter.removeGame(position) }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = gameAdapter

        gamePresenter.loadGames()

        binding.addButton.setOnClickListener {
            val newGame = Game("New Game", "Description", "Genre", 2022)
            gamePresenter.addGame(newGame)
        }

        return binding.root
    }

    override fun showGames(games: List<Game>) {
        gameAdapter.updateGames(games)
    }
}
