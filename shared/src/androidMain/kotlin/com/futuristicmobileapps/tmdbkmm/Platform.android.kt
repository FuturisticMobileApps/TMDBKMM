package com.futuristicmobileapps.tmdbkmm

import java.util.Locale

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun getLocale(): String = Locale.getDefault().country