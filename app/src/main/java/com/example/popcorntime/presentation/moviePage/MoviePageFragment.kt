package com.example.popcorntime.presentation.moviePage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.popcorntime.databinding.FragmentMoviePageBinding


class MoviePageFragment : Fragment() {

    private var _binding: FragmentMoviePageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val args: MoviePageFragmentArgs by navArgs()
        val movieId = args.movieId
        Log.d("movieId", movieId)

        _binding = FragmentMoviePageBinding.inflate(inflater, container, false)
        return binding.root
    }

}