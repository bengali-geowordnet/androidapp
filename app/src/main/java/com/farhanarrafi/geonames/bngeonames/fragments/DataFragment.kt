package com.farhanarrafi.geonames.bngeonames.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.farhanarrafi.geonames.bngeonames.R
import com.farhanarrafi.geonames.bngeonames.model.Data
import com.farhanarrafi.geonames.bngeonames.utility.Constants
import com.farhanarrafi.geonames.bngeonames.utility.Preferences
import com.farhanarrafi.geonames.bngeonames.utility.ResponseCallback
import com.farhanarrafi.geonames.bngeonames.utility.Utils
import com.google.android.gms.location.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_data.view.*
import okhttp3.MediaType
import okhttp3.OkHttpClient


class DataFragment : Fragment(), ResponseCallback {

    val JSON: MediaType? = MediaType.parse("application/json; charset=utf-8")
    private  lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    private var longitude: Double = 0.0
    private var latitude: Double = 0.0
    private var altitude: Double = 0.0
    var url: String = ""
    val client = OkHttpClient()
    lateinit var tv_result:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
        }
        initialize()
    }

    private fun initialize() {
        url = Preferences.get(context, Constants.SERVER_LIST,
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
        view.et_user_key.text = Preferences.get(context,Constants.USER_KEY,"empty")
        view.et_app_key.text = Preferences.get(context,Constants.APP_KEY,"empty")
        view.et_data_longitude.text = (90.405572).toString()
        view.et_data_latitude.text = (23.794437).toString()
        view.et_data_altiutde.text = (5.1).toString()
        tv_result = view.tv_data_result
        view.button.setOnClickListener {
            val userKey = view.et_user_key.text.toString()
            val appKey = view.et_app_key.text.toString()
            val locationName = view.et_location_name.text.toString()
            val locationType = view.et_location_type.text.toString()
            longitude = view.et_data_longitude.text.toString().toDouble()
            latitude = view.et_data_latitude.text.toString().toDouble()
            altitude = view.et_data_altiutde.text.toString().toDouble()
            val data = Data(userKey,appKey,locationName,longitude,latitude,altitude,locationType)
            Utils.sendDataToServer(url, Gson().toJson(data), this)
        }

        return view
    }

    override fun setResult(result: String) {
        Handler(Looper.getMainLooper()).post({
            tv_result.text = result
        })
        Snackbar.make(view!!, result, Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show()
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
}
