package com.haroldadmin.moonshot.models.info

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "headquarters")
data class Headquarters(
    @ColumnInfo(name = "address") val address: String,
    @ColumnInfo(name = "city") val city: String,
    @ColumnInfo(name = "state") val state: String
) {

    @PrimaryKey(autoGenerate = true) var id: Int? = null

}