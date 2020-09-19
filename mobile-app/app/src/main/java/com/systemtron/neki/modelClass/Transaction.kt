package com.systemtron.neki.modelClass

import java.io.FileDescriptor

class Transaction {
    var description: String = ""
    var toName: String = ""
    var toEmail: String = ""
    var fromName: String = ""
    var fromEmail: String = ""
    var fromPhone: String = ""
    var fromStreetAddress: String = ""
    var fromLandmark: String = ""
    var fromPincode: String = ""
    var date: String = ""
    var status = ""

    constructor() {

    }

    constructor(
        description: String,
        toName: String,
        toEmail: String,
        fromName: String,
        fromEmail: String,
        fromPhone: String,
        fromStreetAddress: String,
        fromLandmark: String,
        fromPincode: String,
        date: String,
        status: String
    ) {
        this.description = description
        this.toName = toName
        this.toEmail = toEmail
        this.fromName = fromName
        this.fromEmail = fromEmail
        this.fromPhone = fromPhone
        this.fromStreetAddress = fromStreetAddress
        this.fromLandmark = fromLandmark
        this.fromPincode = fromPincode
        this.date = date
        this.status = status
    }
}