package com.systemtron.neki.modelClass

class User {
    var fullName: String = ""
    var phoneNumber: String = ""
    var streetAddress: String = ""
    var landmark: String = ""
    var state: String = ""
    var city: String = ""
    var pincode: String = ""
    var urlPP: String = ""

    constructor() {
    }

    constructor(
        fullName: String,
        phoneNumber: String,
        streetAddress: String,
        landmark: String,
        state: String,
        city: String,
        pincode: String,
        url: String
    ) {
        this.fullName = fullName
        this.phoneNumber = phoneNumber
        this.streetAddress = streetAddress
        this.landmark = landmark
        this.state = state
        this.city = city
        this.pincode = pincode
        this.urlPP = url
    }
}