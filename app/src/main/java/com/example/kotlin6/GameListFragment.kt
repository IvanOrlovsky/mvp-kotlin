package com.example.kotlin6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin6.data.Game
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.lifecycle.ViewModelProvider


class GameListFragment : Fragment() {

    private lateinit var gameViewModel: GameViewModel
    private lateinit var gameAdapter: GameAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_game_list, container, false)

        gameViewModel = ViewModelProvider(requireActivity()).get(GameViewModel::class.java)

        gameAdapter = GameAdapter(
            mutableListOf(),
            { game, position ->
                val action = GameListFragmentDirections.actionGameListFragmentToGameDetailFragment(game, position)
                findNavController().navigate(action)
            },
            { position ->
                gameViewModel.removeGame(position)
            }
        )

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = gameAdapter

        view.findViewById<FloatingActionButton>(R.id.addButton).setOnClickListener {
            val newGame = Game("New Game", "Description", "Genre", 2022)
            gameViewModel.addGame(newGame)
        }

        gameViewModel.games.observe(viewLifecycleOwner) { games ->
            gameAdapter.updateGames(games)
        }

        return view
    }
}
