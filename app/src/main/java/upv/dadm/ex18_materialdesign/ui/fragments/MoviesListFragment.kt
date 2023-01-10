/*
 * Copyright (c) 2022
 * David de Andrés and Juan Carlos Ruiz
 * Development of apps for mobile devices
 * Universitat Politècnica de València
 */

package upv.dadm.ex18_materialdesign.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.ViewCompat
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.chip.Chip
import upv.dadm.ex18_materialdesign.R
import upv.dadm.ex18_materialdesign.databinding.FragmentMoviesListBinding
import upv.dadm.ex18_materialdesign.ui.adapters.MovieListAdapter
import upv.dadm.ex18_materialdesign.ui.viewmodels.MoviesViewModel

class MoviesListFragment : Fragment(R.layout.fragment_movies_list) {

    private val viewModel: MoviesViewModel by activityViewModels()

    private var _binding: FragmentMoviesListBinding? = null
    private val binding
        get() = _binding!!

    private val adapter = MovieListAdapter(::onClick)
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMoviesListBinding.bind(view)

        binding.recyclerViewFilms.adapter = adapter
        (binding.recyclerViewFilms.layoutManager as LinearLayoutManager).scrollToPosition(
            viewModel.scrollPosition.value ?: 0
        )
        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            adapter.submitList(movies)
        }

        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet.bottomSheetFrameLayout)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

        binding.bottomSheet.dragHandle.setOnClickListener {
            when (bottomSheetBehavior.state) {
                BottomSheetBehavior.STATE_COLLAPSED -> bottomSheetBehavior.state =
                    BottomSheetBehavior.STATE_EXPANDED
                BottomSheetBehavior.STATE_EXPANDED -> bottomSheetBehavior.state =
                    BottomSheetBehavior.STATE_COLLAPSED
                else -> {}

            }
        }

        binding.bottomSheet.bSelectAll.setOnClickListener {
            binding.bottomSheet.cgGenre.forEach { child ->
                (child as Chip).isChecked = true
            }
        }
        binding.bottomSheet.bClearAll.setOnClickListener {
            binding.bottomSheet.cgGenre.forEach { child ->
                (child as Chip).isChecked = false
            }
        }

        var chip: Chip
        var keys: Set<Int>?
        viewModel.genresList.forEach { genre ->
            chip = layoutInflater.inflate(R.layout.chip_genre, null, false) as Chip
            chip.apply {
                keys =
                    viewModel.chipViewsMap.value?.filter { entry -> entry.value == genre.id }?.keys
                if (keys.isNullOrEmpty()) {
                    id = ViewCompat.generateViewId()
                    viewModel.addChipViewIdToMap(id, genre.id)
                } else {
                    id = keys!!.first()
                }
                text = genre.name
                isChecked = true
            }
            binding.bottomSheet.cgGenre.addView(chip)
        }
        binding.bottomSheet.cgGenre.setOnCheckedStateChangeListener { _, ids ->
            viewModel.setSelectedGenres(ids)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onClick(position: Int) {
        viewModel.setSelectedMovie(position)
        viewModel.setScrollPosition((binding.recyclerViewFilms.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition())
        findNavController().currentDestination?.getAction(R.id.actionShowMovieDetail)?.run {
            findNavController().navigate(R.id.actionShowMovieDetail)
        }
    }
}