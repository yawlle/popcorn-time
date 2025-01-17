package com.example.popcorntime.presentation.moviePage

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.popcorntime.PopcornTimeApplication
import com.example.popcorntime.databinding.FragmentMoviePageBinding
import com.example.popcorntime.presentation.common.observeNavBack
import javax.inject.Inject


class MoviePageFragment : Fragment() {

    private var _binding: FragmentMoviePageBinding? = null
    private val binding get() = _binding!!

    val args: MoviePageFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as PopcornTimeApplication)
            .appComponent
            .inject(this)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val moviePageViewModel: MoviePageViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMoviePageBinding.inflate(inflater, container, false)
        binding.vm = moviePageViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val vm = moviePageViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        vm.getMovie(args.movieId)
        observeNavBack(vm.btnBackClicked)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}