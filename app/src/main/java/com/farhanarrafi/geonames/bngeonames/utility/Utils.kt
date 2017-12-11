package com.farhanarrafi.geonames.bngeonames.utility

import android.util.Log
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
                if(response!!.isSuccessful) {
                    val responseString = response!!.body()!!.string()
                    val gson = Gson()
                    try {
                        val status: String = gson.fromJson(responseString!!, JsonObject::class.java).asJsonObject.get("status").toString().replace("\"", "")
                        responseCallback.setResult(status)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Log.e("response", response.toString())
                    }
                } else if(response.code() in 400..499) {
                    responseCallback.setError("You made an error: response.code()")
                } else if(response.code() in 500..599) {
                    responseCallback.setError("Server Error: response.code()")
                } else {
                    responseCallback.setError(response.toString())
                }
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
                if(response!!.isSuccessful) {
                    val responseString = response!!.body()!!.string()
                    val gson = Gson()
                    try {
                        val token: String = gson.fromJson(responseString!!, JsonObject::class.java).asJsonObject.get("token").toString().replace("\"", "")
                        responseCallback.setResult(token)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Log.e("response", response.toString())
                    }
                } else if(response.code() in 400..499) {
                    responseCallback.setError("You made an error: response.code()")
                } else if(response.code() in 500..599) {
                    responseCallback.setError("Server Error: response.code()")
                } else {
                    responseCallback.setError(response.toString())
                }
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