/*
 * Copyright (c) 2022-2024 Universitat Politècnica de València
 * Authors: David de Andrés and Juan Carlos Ruiz
 *          Fault-Tolerant Systems
 *          Instituto ITACA
 *          Universitat Politècnica de València
 *
 * Distributed under MIT license
 * (See accompanying file LICENSE.txt)
 */

package upv.dadm.ex18_materialdesign.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import upv.dadm.ex18_materialdesign.R
import upv.dadm.ex18_materialdesign.databinding.BottomSheetCommentBinding
import upv.dadm.ex18_materialdesign.ui.viewmodels.MoviesViewModel

/**
 * Displays a form, as a BottomSheet, to add a comment to a movie.
 * (This is fake, the comment is not actually added to the movie).
 */
class AddCommentBottomSheet : BottomSheetDialogFragment(R.layout.bottom_sheet_comment) {

    // Reference to a ViewModel shared between Fragments
    private val viewModel: MoviesViewModel by activityViewModels()

    // Backing property to resource binding
    private var _binding: BottomSheetCommentBinding? = null

    // Property valid between onCreateView() and onDestroyView()
    private val binding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Get the automatically generated view binding for the layout resource
        _binding = BottomSheetCommentBinding.bind(view)
        // Add the comment.
        // It actually just displays a Snackbar and dismisses the dialog
        binding.bAddComment.setOnClickListener {
            viewModel.setCommentAdded(true)
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Clear resources to make them eligible for garbage collection
        _binding = null
    }
}