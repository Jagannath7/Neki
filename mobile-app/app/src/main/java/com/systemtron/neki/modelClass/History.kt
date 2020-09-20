package com.systemtron.neki.modelClass

class History {
    var toName: String = ""
    var fromName: String = ""
    var date: String = ""
    var description: String = ""

    constructor() {

    }

    constructor(toName: String, fromName: String, date: String, description: String) {
        this.toName = toName
        this.fromName = fromName
        this.date = date
        this.description = description
    }
}