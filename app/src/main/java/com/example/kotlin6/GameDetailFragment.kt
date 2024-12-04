package com.example.kotlin6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class GameDetailFragment : Fragment() {

    private val args: GameDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game_detail, container, false)

        view.findViewById<TextView>(R.id.detailTitle).text = args.game.title
        view.findViewById<TextView>(R.id.detailDescription).text = args.game.description
        view.findViewById<TextView>(R.id.detailGenre).text = args.game.genre
        view.findViewById<TextView>(R.id.detailYear).text = args.game.year.toString()

        view.findViewById<Button>(R.id.editButton).setOnClickListener {
            val action = GameDetailFragmentDirections.actionGameDetailFragmentToGameEditFragment(args.game, args.position)
            findNavController().navigate(action)
        }

        return view
    }
}