/*
 * Copyright (c) 2022
 * David de Andrés and Juan Carlos Ruiz
 * Development of apps for mobile devices
 * Universitat Politècnica de València
 */

package upv.dadm.ex18_materialdesign.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import upv.dadm.ex18_materialdesign.data.DataSource
import upv.dadm.ex18_materialdesign.model.Movie

class MoviesViewModel : ViewModel() {

    // Backing property for the list of movies (default list provided by DataSource)
    private var _movies = MutableLiveData(DataSource.getMovies())

    // List of movies
    val movies: LiveData<List<Movie>> = _movies

    // Backing property for the currently selected movie (null by default)
    private var _selectedMovie: MutableLiveData<Movie> = MutableLiveData()

    // Currently selected movie
    val selectedMovie: LiveData<Movie> = _selectedMovie

    // List of movie genres (default list provided by DataSource)
    val genresList = DataSource.getGenresList()

    // Backing property for the map between chips Ids and movie genres (empty by default)
    private var _chipViewsMap = MutableLiveData(mapOf<Int, Int>())

    // Map betweeb chips Ids and movie genres
    val chipViewsMap: LiveData<Map<Int, Int>> = _chipViewsMap

    // Backing property for the current position of the RecyclerView
    // (position of the top visible element, default is 0)
    private var _scrollPosition = MutableLiveData(0)

    // Current position of the RecyclerView (position of the top visible element)
    val scrollPosition: LiveData<Int> = _scrollPosition

    // Backing property for whether a comment has been added (false by default)
    private var _isCommentAdded = MutableLiveData(false)

    // Whether a comment has been added
    val isCommentAdded: LiveData<Boolean> = _isCommentAdded

    /**
     * Sets the currently selected movie according to its position in the list.
     */
    fun setSelectedMovie(position: Int) {
        _selectedMovie.value = _movies.value?.get(position)
    }

    /**
     * Sets the current position of the RecyclerView (top element current visible).
     */
    fun setScrollPosition(position: Int) {
        _scrollPosition.value = position
    }

    /**
     * Adds a new pair chip Id and movie genre to the map.
     */
    fun addChipViewIdToMap(viewId: Int, id: Int) {
        _chipViewsMap.value = _chipViewsMap.value?.plus(viewId to id)
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
            genreId = _chipViewsMap.value?.get(viewId)
            // Add this genre Id to the list
            if (genreId != null) {
                genresList.add(genreId!!)
            }
        }
        // Get from DataSource the list of movies filtered by the list of genres Ids
        _movies.value = DataSource.filterMoviesByGenre(genresList)
    }

    /**
     * Sets whether a new comment has been added.
     */
    fun setCommentAdded(added: Boolean) {
        _isCommentAdded.value = added
    }

}