package com.farhanarrafi.geonames.bngeonames.utility

import android.content.Context
import android.content.SharedPreferences


class Preferences() {

    companion object {
        fun get(context: Context, key: String, default: String): String {
            val sharedPref: SharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE)
            return sharedPref.getString(key, default)
        }

        fun set(context: Context, key: String, default: String) {
            val sharedPref: SharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString(key, default)
            editor.apply()

        }

        fun get(context: Context, key: String, default: Boolean): Boolean {
            val sharedPref: SharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE)
            return sharedPref.getBoolean(key, default)
        }

        fun set(context: Context, key: String, default: Boolean) {
            val sharedPref: SharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putBoolean(key, default)
            editor.apply()

        }
    }


}