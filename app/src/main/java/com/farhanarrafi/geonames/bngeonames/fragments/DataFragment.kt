package com.farhanarrafi.geonames.bngeonames.fragments

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.farhanarrafi.geonames.bngeonames.Constants
import com.farhanarrafi.geonames.bngeonames.Preferences
import com.farhanarrafi.geonames.bngeonames.R
import com.farhanarrafi.geonames.bngeonames.model.Data
import com.google.android.gms.location.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_data.view.*
import okhttp3.*
import java.io.IOException


class DataFragment : Fragment() {

    val JSON: MediaType? = MediaType.parse("application/json; charset=utf-8")
    private  lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    private var longitude: Double = 0.0
    private var latitude: Double = 0.0
    private var altitude: Double = 0.0
    var url: String = ""
    val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
        }
        initialize()
    }

    private fun initialize() {
        url = Preferences.get(context,Constants.SERVER_LIST,
                Constants.DEFAULT_SERVER_URL) + Constants.DATA_URL
        setLocationParams()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        if(Preferences.get(context,
                Constants.PERMISSION_FOR_LOCATION_GRANTED,false)) {
            try {
                fusedLocationClient.requestLocationUpdates(locationRequest,locationCallback,null)
            } catch (e:SecurityException) {
                e.printStackTrace()
            }
        }
    }


    private fun setLocationParams() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult?) {
                for (location in result!!.locations) {
                    longitude = location.longitude
                    latitude = location.latitude
                    altitude = location.altitude

                }
            }
        }
        locationRequest = LocationRequest()
        locationRequest.interval = 10000
        locationRequest.fastestInterval = 5000
        locationRequest.priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view :View = inflater!!.inflate(R.layout.fragment_data, container, false)
        view.button.setOnClickListener {
            val userKey = view.et_user_key.text.toString()
            val appKey = view.et_app_key.text.toString()
            val locationName = view.et_location_name.text.toString()
            val locationType = view.et_location_type.text.toString()
            longitude = view.et_data_longitude.text.toString().toDouble()
            latitude = view.et_data_latitude.text.toString().toDouble()
            altitude = view.et_data_altiutde.text.toString().toDouble()
            val data = Data(userKey,appKey,locationName,longitude,latitude,altitude,locationType)
            post(url, Gson().toJson(data))
        }

        return view
    }

    companion object {
        /**
         * @return A new instance of fragment DataFragment.
         */
        fun newInstance(): DataFragment {
            val fragment = DataFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    fun post(url: String, json: String) {
        var body: RequestBody = RequestBody.create(JSON, json)
        var request: Request = Request.Builder()
                .url(url)
                .post(body)
                .build()

        client.newCall(request).enqueue(callback);
    }

    var callback: Callback = object : Callback {
        override fun onFailure(call: Call?, e: IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: Call?, response: Response?) {
            Log.d("POST", response.toString())
            Snackbar.make(view!!, "response.toString()", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show()
        }
    }

}
