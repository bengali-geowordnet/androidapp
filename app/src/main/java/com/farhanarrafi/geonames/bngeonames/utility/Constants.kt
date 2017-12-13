package com.farhanarrafi.geonames.bngeonames.utility


class Constants {

    companion object {
        val PREF_KEY_DEFAULT_SERVER_URL: String = "pref_key_default_server_url"
        val APP_URL: String = "api/app/"
        val USER_URL: String = "api/user/"
        val DATA_URL: String = "api/data/"
        val APP_KEY: String = "com.farhanarrafi.geonames.application_key"
        val USER_KEY: String = "com.farhanarrafi.geonames.user_key"
        var PERMISSION_FOR_LOCATION: Int = 1005
        var PERMISSION_FOR_LOCATION_GRANTED: String = "permission_for_location_granted"

        val SUCCESS_RESULT = 0
        val FAILURE_RESULT = 1
        val PACKAGE_NAME = "com.farhanarrafi.geonames.bngeonames"
        val RECEIVER = PACKAGE_NAME + ".RECEIVER"
        val RESULT_DATA_KEY = PACKAGE_NAME + ".RESULT_DATA_KEY"
        val LOCATION_DATA_EXTRA = PACKAGE_NAME + ".LOCATION_DATA_EXTRA"
    }

}