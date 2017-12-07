package com.farhanarrafi.geonames.bngeonames.utility

import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.*
import java.io.IOException

class Utils {
    companion object {
        val JSON: MediaType? = MediaType.parse("application/json; charset=utf-8")
        lateinit var responseCallback: ResponseCallback

        fun sendDataToServer(url: String, json: String, responseCallback: ResponseCallback) {
            Companion.responseCallback = responseCallback
            OkHttpClient().newCall(getRequest(url,json)).enqueue(sendDataCallback);
        }

        private var sendDataCallback: Callback = object : Callback {
            override fun onFailure(call: Call?, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call?, response: Response?) {
                val responseString = response!!.body()!!.string()
                val gson = Gson()
                val status: String = gson.fromJson(responseString!!, JsonObject::class.java).asJsonObject.get("status").toString().replace("\"", "")
                responseCallback.setResult(status)
            }
        }

        fun requestTokenFromServer(url: String, json: String, responseCallback: ResponseCallback) {
            Companion.responseCallback = responseCallback
            OkHttpClient().newCall(getRequest(url,json)).enqueue(tokenCallback);
        }

        private var tokenCallback: Callback = object : Callback {
            override fun onFailure(call: Call?, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call?, response: Response?) {
                val responseString = response!!.body()!!.string()
                val gson = Gson()
                val token: String = gson.fromJson(responseString!!, JsonObject::class.java).asJsonObject.get("token").toString().replace("\"", "")
                responseCallback.setResult(token)
            }
        }

        fun getRequest(url: String, json: String): Request {
            val body: RequestBody = RequestBody.create(JSON, json)
            return Request.Builder()
                    .url(url)
                    .addHeader("Content-type", "application/json")
                    .post(body)
                    .build()
        }
    }
}