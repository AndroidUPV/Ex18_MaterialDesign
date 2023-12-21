/*
 * Copyright (c) 2022-2023 Universitat Politècnica de València
 * Authors: David de Andrés and Juan Carlos Ruiz
 *          Fault-Tolerant Systems
 *          Instituto ITACA
 *          Universitat Politècnica de València
 *
 * Distributed under MIT license
 * (See accompanying file LICENSE.txt)
 */

package upv.dadm.ex18_materialdesign.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
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

        // Display a message to confirm that the comment has been added to the movie
        viewModel.isCommentAdded.observe(viewLifecycleOwner) { added -> commentAdded(added) }
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
        // If the main FAB is not visible, change the small FAB layout properties
        // to set it at the bottom-end of the screen
        if (movie.link.isEmpty()) {
            binding.fabComment.updateLayoutParams<CoordinatorLayout.LayoutParams> {
                anchorId = View.NO_ID
                gravity = Gravity.BOTTOM or Gravity.END
            }
        }

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

    /**
     * Display a message to acknowledge the comment has been added.
     */
    private fun commentAdded(added: Boolean) {
        if (added) {
            // Display the message
            Snackbar.make(
                binding.coordinatorLayoutDetail,
                R.string.comment_added,
                Snackbar.LENGTH_SHORT
            ).show()
            // Deactivate the flag from the ViewModel
            viewModel.setCommentAdded(false)
        }
    }
}