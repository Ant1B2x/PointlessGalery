package com.example.gallery

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Picture(
    @PrimaryKey(autoGenerate = true) val pictureID: Int = 0,
    @ColumnInfo(name = "blob", typeAffinity = ColumnInfo.BLOB) val blob: ByteArray,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "location") val location: String,
    @ColumnInfo(name = "favorite") val favorite: Boolean = false
)
