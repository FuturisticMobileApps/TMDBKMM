package com.futuristicmobileapps.tmdbkmm

import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

actual fun getLocale(): String = NSLocale.currentLocale().objectForKey(NSLocaleCountryCode) as! String