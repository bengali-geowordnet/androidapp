package com.farhanarrafi.geonames.bngeonames.model


public class User{
    var name: String = ""
    var email: String = ""
    var phone: String = ""

    constructor(name: String, email: String, phone: String) {
        this.name = name
        this.email = email
        this.phone = phone
    }

    fun getJSON(): String {
        return "{'name' : $name, 'email' : $email, 'phone' : $phone }"
    }
}