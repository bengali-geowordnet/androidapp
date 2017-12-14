package com.farhanarrafi.geonames.bngeonames.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.farhanarrafi.geonames.bngeonames.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_location_selection.*

class LocationSelectionActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_selection)

        onEventListeners()

        val mapFragment = map_fragment as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun onEventListeners() {

        btnMap.setOnClickListener {
            map.mapType = GoogleMap.MAP_TYPE_NORMAL
        }

        btnHybrid.setOnClickListener {
            map.mapType = GoogleMap.MAP_TYPE_HYBRID
        }

        btnSatellite.setOnClickListener {
            map.mapType =GoogleMap.MAP_TYPE_SATELLITE
        }
    }

    override fun onMapReady(map: GoogleMap) {
        this.map = map
        val latlong = LatLng(23.794437,90.405572)
        val target = CameraPosition.builder().target(latlong).zoom(17.0f).tilt(65.0f).build()
        map.moveCamera(CameraUpdateFactory.newCameraPosition(target))
    }
}
