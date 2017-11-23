package com.farhanarrafi.geonames.bngeonames.model

class Data {
    private var userKey: String = ""
    private var appKey: String = ""
    private var name: String = ""
    private var longitude: Double = 0.0
    private var latitude: Double = 0.0
    private var altitude: Int = 0
    private var elevation: Int = 0
    private var type: String = ""

    constructor(userKey: String, appKey: String, name: String, longitude: Double, latitude: Double, altitude: Int, elevation: Int, type: String) {
        this.userKey = userKey
        this.appKey = appKey
        this.name = name
        this.longitude = longitude
        this.latitude = latitude
        this.altitude = altitude
        this.elevation = elevation
        this.type = type
    }


    fun getJSON(): String {
        return "{ 'appKey': '$appKey', " +
                "'userKey': '$userKey'," +
                "'location': { 'name': '$name', " +
                "'longitude': $longitude, " +
                "'latitude': $latitude, " +
                "'altitude': $altitude, " +
                "'elevation': $elevation, " +
                "'type': '$type' }, " +
                "'region': { " +
                "'name': '', " +
                "'type': '' } }"
    }
}