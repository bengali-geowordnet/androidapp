package com.farhanarrafi.geonames.bngeonames.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.farhanarrafi.geonames.bngeonames.R
import com.farhanarrafi.geonames.bngeonames.model.User
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    val JSON: MediaType? = MediaType.parse("application/json; charset=utf-8")
    val client = OkHttpClient()
    var url = "https://requestb.in/12mmaip1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun post(url: String, json: String) {
        var body: RequestBody = RequestBody.create(JSON, json)
        var request: Request = Request.Builder()
                .url(url)
                .post(body)
                .build()

        client.newCall(request).enqueue(callback);
    }

    fun sendRequest(view: View) {

        val user = User(tv_name.text.toString(), tv_type.text.toString(), tv_email.text.toString())
        post(url, Gson().toJson(user))

    }

    var callback: Callback = object : Callback {
        override fun onFailure(call: Call?, e: IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: Call?, response: Response?) {
            Log.d("POST", response.toString())
        }
    }
}

