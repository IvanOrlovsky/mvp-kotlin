package com.example.kotlin6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kotlin6.databinding.FragmentGameDetailBinding

class GameDetailFragment : Fragment() {

    private val args: GameDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentGameDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameDetailBinding.inflate(inflater, container, false)

        // Заполняем данные игры
        binding.detailTitle.text = args.game.title
        binding.detailDescription.text = args.game.description
        binding.detailGenre.text = args.game.genre
        binding.detailYear.text = args.game.year.toString()

        // Обработчик для кнопки "Edit"
        binding.editButton.setOnClickListener {
            val action = GameDetailFragmentDirections.actionGameDetailFragmentToGameEditFragment(args.game, args.position)
            findNavController().navigate(action)  // Используем findNavController() внутри фрагмента
        }

        return binding.root
    }
}
