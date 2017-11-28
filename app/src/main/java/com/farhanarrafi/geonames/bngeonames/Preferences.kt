package com.farhanarrafi.geonames.bngeonames

import android.content.Context
import android.content.SharedPreferences



/**
 * Created by bjit on 11/27/17.
 */
class Preferences(){

    companion object {
        fun getSharedPrefrences(context: Context, key:String, default: String): String {
            val sharedPref: SharedPreferences = context.getSharedPreferences(key,Context.MODE_PRIVATE)
            return sharedPref.getString(key, default)
        }

        fun setSharedPrefrences(context: Context, key:String, default:String) {
            val sharedPref: SharedPreferences = context.getSharedPreferences(key,Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString(key, default)
            editor.apply()

        }

        fun getSharedPrefrences(context: Context, key:String, default: Boolean): Boolean {
            val sharedPref: SharedPreferences = context.getSharedPreferences(key,Context.MODE_PRIVATE)
            return sharedPref.getBoolean(key, default)
        }

        fun setSharedPrefrences(context: Context, key:String, default:Boolean) {
            val sharedPref: SharedPreferences = context.getSharedPreferences(key,Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putBoolean(key, default)
            editor.apply()

        }
    }


}