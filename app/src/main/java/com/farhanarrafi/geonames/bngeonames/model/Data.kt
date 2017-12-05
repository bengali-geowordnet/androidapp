package com.farhanarrafi.geonames.bngeonames.model

class Data {
    private var userKey: String = ""
    private var appKey: String = ""
    private var name: String = ""
    private var longitude: Double = 0.0
    private var latitude: Double = 0.0
    private var altitude: Double = 0.0
    private var type: String = ""

    constructor(userKey: String, appKey: String, name: String, longitude: Double, latitude: Double, altitude: Double, type: String) {
        this.userKey = userKey
        this.appKey = appKey
        this.name = name
        this.longitude = longitude
        this.latitude = latitude
        this.altitude = altitude
        this.type = type
    }
}