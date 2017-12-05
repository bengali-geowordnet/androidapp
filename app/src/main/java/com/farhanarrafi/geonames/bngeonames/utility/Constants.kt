package com.farhanarrafi.geonames.bngeonames.utility


class Constants {

    companion object {
        val SERVER_LIST: String = "server_list"
        //val DEFAULT_SERVER_URL: String = "http://192.168.19.77:8899/"
        val DEFAULT_SERVER_URL: String = "http://geocrowdsource-geosource.193b.starter-ca-central-1.openshiftapps.com/"
        val APP_URL: String = "api/app/"
        val USER_URL: String = "api/user/"
        val DATA_URL: String = "api/data/"
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