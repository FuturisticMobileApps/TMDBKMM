package com.futuristicmobileapps.tmdbkmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun getLocale() : String