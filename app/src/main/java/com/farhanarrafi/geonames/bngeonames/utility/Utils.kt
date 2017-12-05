package com.farhanarrafi.geonames.bngeonames.utility

import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.*
import java.io.IOException

class Utils {
    companion object {
        val JSON: MediaType? = MediaType.parse("application/json; charset=utf-8")
        lateinit var responseCallback: ResponseCallback


        fun requestTokenFromServer(url: String, json: String, responseCallback: ResponseCallback) {
            Companion.responseCallback = responseCallback
            var body: RequestBody = RequestBody.create(JSON, json)
            var request: Request = Request.Builder()
                    .url(url)
                    .addHeader("Content-type", "application/json")
                    .post(body)
                    .build()

            OkHttpClient().newCall(request).enqueue(callback);
        }

        var callback: Callback = object : Callback {
            override fun onFailure(call: Call?, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call?, response: Response?) {
                val responseString = response!!.body()!!.string()
                val gson = Gson()
                val token: String = gson.fromJson(responseString!!, JsonObject::class.java).asJsonObject.get("token").toString().replace("\"", "")
                responseCallback.setToken(token)
            }
        }
    }
}