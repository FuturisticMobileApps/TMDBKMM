package com.futuristicmobileapps.tmdbkmm.viewmodel

import com.futuristicmobileapps.tmdbkmm.models.MoviesWrapper
import com.futuristicmobileapps.tmdbkmm.repository.MovieRepository
import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import com.rickclephas.kmm.viewmodel.coroutineScope
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainScreenViewModel : KMMViewModel() {

    private val repository: MovieRepository = MovieRepository()

    private val _uiState = MutableStateFlow<UiState>(viewModelScope, UiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.coroutineScope.launch {
            try {

                val movies = repository.getAllMovies(getDeviceRegion())
                _uiState.update { UiState.Data(movies) }
            } catch (ex: Exception) {
                _uiState.update { UiState.Error("Api Failure") }
            }
        }
    }

    private fun getDeviceRegion(): String = "IN"


    sealed interface UiState {
        data class Data(
            val movies: MoviesWrapper
        ) : UiState

        data class Error(
            val errorMessage: String
        ) : UiState

        object Loading : UiState
    }
}