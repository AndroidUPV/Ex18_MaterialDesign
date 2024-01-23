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

package upv.dadm.ex18_materialdesign.ui.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import upv.dadm.ex18_materialdesign.data.DataSource
import upv.dadm.ex18_materialdesign.model.Movie

/**
 * Holds information related to the list of movies.
 */
class MoviesViewModel : ViewModel() {

    // Backing property for the list of movies (default list provided by DataSource)
    private val _movies = MutableStateFlow(DataSource.getMovies())

    // List of movies
    val movies = _movies.asStateFlow()

    // Backing property for the currently selected movie (null by default)
    private val _selectedMovie = MutableStateFlow<Movie?>(null)

    // Currently selected movie
    val selectedMovie = _selectedMovie.asStateFlow()

    // List of movie genres (default list provided by DataSource)
    val genresList = DataSource.getGenresList()

    // Map between chips Ids and movie genres (empty by default)
    private val _chipViewsMap = MutableStateFlow(mapOf<Int, Int>())

    // Backing property for the map between chips Ids and movie genres
    val chipViewsMap = _chipViewsMap.asStateFlow()

    // Backing property for the current position of the RecyclerView
    // (position of the top visible element, default is 0)
    private val _scrollPosition = MutableStateFlow(0)

    // Current position of the RecyclerView (position of the top visible element)
    val scrollPosition = _scrollPosition.asStateFlow()

    // Backing property for whether a comment has been added (false by default)
    private val _isCommentAdded = MutableStateFlow(false)

    // Whether a comment has been added
    val isCommentAdded = _isCommentAdded.asStateFlow()

    /**
     * Sets the currently selected movie according to its position in the list.
     */
    fun setSelectedMovie(position: Int) {
        _selectedMovie.update { _movies.value[position] }
    }

    /**
     * Sets the current position of the RecyclerView (top element current visible).
     */
    fun setScrollPosition(position: Int) {
        _scrollPosition.update { position }
    }

    /**
     * Adds a new pair chip Id and movie genre to the map.
     */
    fun addChipViewIdToMap(viewId: Int, id: Int) {
        _chipViewsMap.update { map ->
            map.plus(viewId to id)
        }
    }

    /**
     * Gets a new list of movies matching the genres identified by the selected chips.
     */
    fun getMoviesByGenre(viewIdsList: List<Int>) {
        val genresList = mutableListOf<Int>()
        var genreId: Int?
        // Go through the whole list of selected chips
        viewIdsList.forEach { viewId ->
            // Get the genre identified by the selected chip
            genreId = _chipViewsMap.value[viewId]
            // Add this genre Id to the list
            if (genreId != null) {
                genresList.add(genreId!!)
            }
        }
        // Get from DataSource the list of movies filtered by the list of genres Ids
        _movies.update { DataSource.filterMoviesByGenre(genresList) }
    }

    /**
     * Sets whether a new comment has been added.
     */
    fun setCommentAdded(added: Boolean) {
        _isCommentAdded.update { added }
    }

}