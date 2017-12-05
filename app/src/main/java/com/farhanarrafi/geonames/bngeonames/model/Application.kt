package com.farhanarrafi.geonames.bngeonames.model


class Application{
    private var name: String = ""
    private var email: String = ""
    private var type: String = ""

    constructor(name: String, email: String, type: String) {
        this.name = name
        this.email = email
        this.type = type
    }

}