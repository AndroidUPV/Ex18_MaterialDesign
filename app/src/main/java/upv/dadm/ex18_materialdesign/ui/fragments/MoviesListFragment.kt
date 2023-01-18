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
import com.google.android.material.snackbar.Snackbar
import upv.dadm.ex18_materialdesign.R
import upv.dadm.ex18_materialdesign.databinding.FragmentMoviesListBinding
import upv.dadm.ex18_materialdesign.model.Movie
import upv.dadm.ex18_materialdesign.ui.adapters.MovieListAdapter
import upv.dadm.ex18_materialdesign.ui.viewmodels.MoviesViewModel

/**
 * Displays the list of movies (in a RecyclerView) according to
 * the selected movie genres (a ChipGroup within a standard BottomSheet).
 */
class MoviesListFragment : Fragment(R.layout.fragment_movies_list) {

    // Reference to a ViewModel shared between Fragments
    private val viewModel: MoviesViewModel by activityViewModels()

    // Backing property to resource binding
    private var _binding: FragmentMoviesListBinding? = null

    // Property valid between onCreateView() and onDestroyView()
    private val binding
        get() = _binding!!

    private val adapter = MovieListAdapter(::onClick)
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Get the automatically generated view binding for the layout resource
        _binding = FragmentMoviesListBinding.bind(view)

        // Adapter for the RecyclerView with Vertical LinearLayoutManager
        binding.recyclerViewFilms.adapter = adapter
        // Keep the previous position of the LinearLayoutManager (if any, to top (0) otherwise)
        (binding.recyclerViewFilms.layoutManager as LinearLayoutManager).scrollToPosition(
            viewModel.scrollPosition.value ?: 0
        )
        // Submit a new list to be displayed whenever the list of movies changes (due to filtering)
        viewModel.movies.observe(viewLifecycleOwner) { movies -> updateMovieList(movies) }

        // Get the BottomSheetBehavior from the BottomSheet
        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet.bottomSheetFrameLayout)
        // Collapse the BottomSheet by default
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        // Clicking the BottomSheetDragViewHandle collapses/expands the BottomSheet
        binding.bottomSheet.dragHandle.setOnClickListener { toggleBottomSheetState() }
        // Check all Chips in the ChipGroup
        binding.bottomSheet.bSelectAll.setOnClickListener { checkAllChips() }
        // Uncheck all Chips in the ChipGroup
        binding.bottomSheet.bClearAll.setOnClickListener {
            binding.bottomSheet.cgGenre.clearCheck()
        }

        setUpChips()
        // Update the list of selected genres (Chips Ids) whenever the state of the ChipGroup changes
        binding.bottomSheet.cgGenre.setOnCheckedStateChangeListener { _, ids ->
            viewModel.getMoviesByGenre(ids)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Clear resources to make them eligible for garbage collection
        _binding = null
    }

    /**
     * Updates the list of movies to be displayed.
     */
    private fun updateMovieList(movies: List<Movie>) {
        adapter.submitList(movies)
        // Display a message (Snackbar) when the list is empty.
        // It can be swiped to dismiss.
        if (movies.isEmpty()) {
            Snackbar.make(
                binding.coordinatorLayoutFilms,
                R.string.no_movies,
                Snackbar.LENGTH_SHORT
            ).show()
        }

    }

    /**
     * Expands/Collapses the BottomSheet if collapsed/expanded.
     */
    private fun toggleBottomSheetState() {
        when (bottomSheetBehavior.state) {
            BottomSheetBehavior.STATE_COLLAPSED -> bottomSheetBehavior.state =
                BottomSheetBehavior.STATE_EXPANDED
            BottomSheetBehavior.STATE_EXPANDED -> bottomSheetBehavior.state =
                BottomSheetBehavior.STATE_COLLAPSED
            else -> {}
        }
    }

    /**
     * Checks all Chips within the ChipGroup.
     */
    private fun checkAllChips() {
        binding.bottomSheet.cgGenre.forEach { child ->
            (child as Chip).isChecked = true
        }
    }

    /**
     * Dynamically add a Chip for each available movie genre.
     * The view Id is automatically generated and stored for later use when recreating the Fragment.
     */
    private fun setUpChips() {
        var chip: Chip
        var keys: Set<Int>?
        // Create a new Chip for each movie genre
        viewModel.genresList.forEach { genre ->
            // Inflate the View
            chip = layoutInflater.inflate(R.layout.chip_genre, null, false) as Chip
            chip.apply {
                // Obtain the keys for any key-value pair matching a given movie genre
                keys =
                    viewModel.chipViewsMap.value?.filter { entry -> entry.value == genre.id }?.keys
                // If there is no key yet then automatically generate a new one and
                // associate it to the movie genre
                if (keys.isNullOrEmpty()) {
                    id = ViewCompat.generateViewId()
                    viewModel.addChipViewIdToMap(id, genre.id)
                } else {
                    // Set the retrieved id as the view Id
                    id = keys!!.first()
                }
                text = genre.name
                isChecked = true
            }
            // Add the Chip to the ChipGroup
            binding.bottomSheet.cgGenre.addView(chip)
        }
    }

    /**
     * Displays the details of the selected movie.
     */
    private fun onClick(position: Int) {
        // Keep the selected movie
        viewModel.setSelectedMovie(position)
        // Keep the current position of the LinearLayoutManager (first visible movie)
        viewModel.setScrollPosition(
            (binding.recyclerViewFilms.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        )
        // Navigate to movie detail screen and prevent the same action from triggering twice in a row
        findNavController().currentDestination?.getAction(R.id.actionShowMovieDetail)?.let {
            findNavController().navigate(R.id.actionShowMovieDetail)
        }
    }
}