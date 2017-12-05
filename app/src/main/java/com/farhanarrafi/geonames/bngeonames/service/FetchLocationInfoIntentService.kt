package com.farhanarrafi.geonames.bngeonames.service

import android.app.IntentService
import android.content.Intent
import android.location.Geocoder
import java.util.*


class FetchLocationInfoIntentService(name: String?) : IntentService(name) {

    override fun onHandleIntent(intent: Intent?) {
        val geocoder = Geocoder(this, Locale.getDefault())

        geocoder.getFromLocation(0.0, 0.0, 3)
    }
}