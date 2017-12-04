package com.farhanarrafi.geonames.bngeonames.model


public class Application{
    private var name: String = ""
    private var email: String = ""
    private var type: String = ""

    constructor(name: String, email: String, type: String) {
        this.name = name
        this.email = email
        this.type = type
    }

    fun getJSON(): String {
        return "{'name' : $name, 'email' : $email, 'type' : $type }"
    }
}