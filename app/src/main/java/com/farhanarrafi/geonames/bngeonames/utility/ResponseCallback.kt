package com.farhanarrafi.geonames.bngeonames.utility


interface ResponseCallback {
    fun setResult(result: String) {}
    fun setError(error: String) {}
}