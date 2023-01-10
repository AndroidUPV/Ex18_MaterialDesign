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
import upv.dadm.ex18_materialdesign.R
import upv.dadm.ex18_materialdesign.databinding.FragmentMovieDetailBinding
import upv.dadm.ex18_materialdesign.ui.viewmodels.MoviesViewModel

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private val viewModel: MoviesViewModel by activityViewModels()

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMovieDetailBinding.bind(view)

        val movie = viewModel.selectedMovie.value
        if (movie != null) {
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

        binding.fabWebsite.setOnClickListener {
            val intent = Intent().setAction(Intent.ACTION_VIEW)
                .setData(Uri.parse(viewModel.selectedMovie.value?.link))
            requireActivity().startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}