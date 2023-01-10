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

    private var _movies = MutableLiveData(DataSource.getMovies())
    val movies: LiveData<List<Movie>> = _movies

    private var _selectedMovie: MutableLiveData<Movie> = MutableLiveData()
    val selectedMovie: LiveData<Movie> = _selectedMovie

    val genresList = DataSource.getGenresList()

    private var _chipViewsMap = MutableLiveData(mapOf<Int, Int>())
    val chipViewsMap: LiveData<Map<Int, Int>> = _chipViewsMap

    private var _selectedGenres = MutableLiveData(listOf<Int>())
    val selectedGenres: LiveData<List<Int>> = _selectedGenres

    private var _scrollPosition: MutableLiveData<Int> = MutableLiveData(0)
    val scrollPosition: LiveData<Int> = _scrollPosition

    fun setSelectedMovie(position: Int) {
        _selectedMovie.value = _movies.value?.get(position)
    }

    fun setScrollPosition(position: Int) {
        _scrollPosition.value = position
    }

    fun addChipViewIdToMap(viewId: Int, id: Int) {
        _chipViewsMap.value = _chipViewsMap.value?.plus(viewId to id)
    }

    fun setSelectedGenres(viewIdsList: List<Int>) {
        val genresList = mutableListOf<Int>()
        _selectedGenres.value = viewIdsList
        var genreId: Int?
        viewIdsList.forEach { viewId ->
            genreId = _chipViewsMap.value?.get(viewId)
            if (genreId != null) {
                genresList.add(genreId!!)
            }
        }
        _movies.value = DataSource.filterMoviesByGenre(genresList)
    }

}