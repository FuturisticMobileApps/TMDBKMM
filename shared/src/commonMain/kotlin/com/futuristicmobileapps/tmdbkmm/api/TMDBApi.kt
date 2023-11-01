package com.futuristicmobileapps.tmdbkmm.api

import com.futuristicmobileapps.tmdbkmm.models.MoviesWrapper
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class TMDBApi {

    private val httpClient = HttpClient {
        defaultRequest {
            url {
                headers.append("Authorization", "Bearer $API_KEY")
            }
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
        install(Logging) {
            level = LogLevel.ALL
        }
    }

    suspend fun getAllMovies(region: String): MoviesWrapper {
        return httpClient.get("$BASE_URL/discover/movie") {
            url {
                parameters.append("region", region)
            }
        }.body()

    }

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original"
        const val API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhOTYzYWY3ZmNkNmViMjNlMDQ1MzMyM2M2ZTNkMGE3YyIsInN1YiI6IjVlZWYxZDM0MTBkYWQ2MDAzMmJlNDJhZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.8UG2jvgcPUjTiHGnVkaNASBqW1jWxyMyrTED2dpzJso"
    }
}