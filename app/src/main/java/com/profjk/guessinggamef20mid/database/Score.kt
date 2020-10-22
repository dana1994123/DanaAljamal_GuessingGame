package com.profjk.guessinggamef20mid.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import java.util.*

@Entity(tableName = "Score", primaryKeys = arrayOf("id"))
data class Score(
    @ColumnInfo(name = "id") var id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "username") var username: String,
    @ColumnInfo(name = "attempt_result") var attemptResult: Boolean,
){
    constructor(username: String, attemptResult: Boolean) : this(
        id = UUID.randomUUID().toString(),
        username = username,
        attemptResult = attemptResult)
}