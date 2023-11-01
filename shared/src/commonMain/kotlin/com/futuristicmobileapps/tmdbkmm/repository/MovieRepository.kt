package com.futuristicmobileapps.tmdbkmm.repository

import com.futuristicmobileapps.tmdbkmm.api.TMDBApi
import com.futuristicmobileapps.tmdbkmm.models.MoviesWrapper

class MovieRepository  {

   suspend fun getAllMovies(region : String) : MoviesWrapper = TMDBApi().getAllMovies(region)

}