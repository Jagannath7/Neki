package com.systemtron.neki.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class History(
    @PrimaryKey
    var transactionId: String,
    var date: String,
    var toName: String,
    var description: String
)