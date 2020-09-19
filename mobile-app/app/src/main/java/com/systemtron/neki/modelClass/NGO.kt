package com.systemtron.neki.modelClass

import com.google.firebase.firestore.Exclude

class NGO {
    var categories: String = ""
    var name: String = ""
    var phoneNumber: String = ""
    var streetAddress: String = ""
    var landmark: String = ""
    var listCategory: List<String> = emptyList()

    @get:Exclude
    var emailId: String = ""

    var state: String = ""
    var city: String = ""
    var pincode: String = ""
    var iconUrl: String = ""
    var tagline: String = ""

    constructor() {
    }

    constructor(
        name: String,
        phoneNumber: String,
        streetAddress: String,
        landmark: String,
        state: String,
        categories: String,
        city: String,
        pincode: String,
        iconUrl: String,
        tagline: String
    ) {
        this.categories = categories
        this.name = name
        this.phoneNumber = phoneNumber
        this.streetAddress = streetAddress
        this.landmark = landmark
        this.state = state
        this.city = city
        this.pincode = pincode
        this.iconUrl = iconUrl
        this.tagline = tagline
    }
}