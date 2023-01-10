/*
 * Copyright (c) 2022
 * David de Andrés and Juan Carlos Ruiz
 * Development of apps for mobile devices
 * Universitat Politècnica de València
 */

package upv.dadm.ex18_materialdesign.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import upv.dadm.ex18_materialdesign.R
import upv.dadm.ex18_materialdesign.databinding.FragmentMovieDetailBinding
import upv.dadm.ex18_materialdesign.model.Movie
import upv.dadm.ex18_materialdesign.ui.viewmodels.MoviesViewModel

/**
 * Displays the selected movie cover and overview.
 * A FloatingActionButton gives access to the movie's official website (if any).
 */
class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    // Reference to a ViewModel shared between Fragments
    private val viewModel: MoviesViewModel by activityViewModels()

    // Backing property to resource binding
    private var _binding: FragmentMovieDetailBinding? = null

    // Property valid between onCreateView() and onDestroyView()
    private val binding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Get the automatically generated view binding for the layout resource
        _binding = FragmentMovieDetailBinding.bind(view)

        // Display the data of the selected movie
        viewModel.selectedMovie.value?.let { movie -> setUpMovieDetails(movie) }

        // Launch an implicit Intent to access the movie's website
        binding.fabWebsite.setOnClickListener { accessMovieWebsite() }

        // Add a comment to the movie (fake, it actually does nothing)
        binding.fabComment.setOnClickListener { addComment() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Clear resources to make them eligible for garbage collection
        _binding = null
    }

    /**
     * Displays the details of the selected movie.
     */
    private fun setUpMovieDetails(movie: Movie) {
        binding.tvOverview.text = movie.overview
        binding.clDetail.background =
            ResourcesCompat.getDrawable(
                resources,
                resources.getIdentifier(
                    "p${movie.id}",
                    "drawable",
                    requireContext().packageName
                ),
                requireContext().theme
            )
        binding.fabWebsite.isVisible = movie.link.isNotEmpty()
    }

    /**
     * Launches an implicit Intent to access the selected movie's website
     */
    private fun accessMovieWebsite() {
        val intent = Intent().setAction(Intent.ACTION_VIEW)
            .setData(Uri.parse(viewModel.selectedMovie.value?.link))
        requireActivity().startActivity(intent)
    }

    /**
     * Displays the form to add the comment to the movie.
     */
    private fun addComment() {
        findNavController().navigate(R.id.actionAddComment)
    }
}