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

package upv.dadm.ex18_materialdesign.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import upv.dadm.ex18_materialdesign.databinding.MovieItemBinding
import upv.dadm.ex18_materialdesign.model.Movie

/**
 * Custom adapter to generate the Views required to display the information
 * of movies currently in theatres in a RecyclerView.
 * A ViewHolder keeps the references to all the ViewBindings for each item displayed.
 * Updates are handled by computing the different between the old and the new arrays.
 */

class MovieListAdapter(private val onClick: (Int) -> Unit) :
    ListAdapter<Movie, MovieListAdapter.ViewHolder>(MovieDiffCallback) {

    /**
     * Computes the diff between two movies in the array.
     */
    object MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        /**
         * Determines whether two movies are the same.
         */
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem.id == newItem.id

        /**
         * Determines whether two movies have the same data.
         */
        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem == newItem

    }

    /**
     * Holds a reference to a ViewBinding and
     * sets a listener to react to any click on the View.
     */
    class ViewHolder(private val binding: MovieItemBinding, private val onClick: (Int) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            // This listener will be executed when the View is clicked
            // to display the detail of the movie clicked
            binding.root.setOnClickListener {
                onClick(absoluteAdapterPosition)
            }
        }

        /**
         * Fills the elements within the View with provided Movie object.
         */
        fun bind(item: Movie?) {
            binding.tvTitle.text = item?.title
            val context = binding.ivBackdrop.context
            val resources = binding.ivBackdrop.resources
            binding.ivBackdrop.setImageDrawable(
                ResourcesCompat.getDrawable(
                    resources,
                    resources.getIdentifier("b${item?.id}", "drawable", context.packageName),
                    context.theme
                )
            )
        }

    }

    /**
     * Creates the ViewBinding and attaches it to a ViewHolder
     * to easily access all the elements within the View.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            MovieItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            onClick
        )

    /**
     * Fills the elements within the View with the required data from the array.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}