/*
 * Copyright (c) 2022
 * David de Andrés and Juan Carlos Ruiz
 * Development of apps for mobile devices
 * Universitat Politècnica de València
 */

package upv.dadm.ex18_materialdesign.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import upv.dadm.ex18_materialdesign.databinding.FilmItemBinding
import upv.dadm.ex18_materialdesign.model.Movie

class MovieListAdapter(private val onClick: (Int) -> Unit) :
    ListAdapter<Movie, MovieListAdapter.ViewHolder>(MovieDiffCallback) {

    object MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem == newItem

    }

    class ViewHolder(private val binding: FilmItemBinding, private val onClick: (Int) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onClick(absoluteAdapterPosition)
            }
        }

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            FilmItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            onClick
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}