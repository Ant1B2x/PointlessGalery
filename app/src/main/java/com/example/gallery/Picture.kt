package com.example.gallery

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Picture(
    @PrimaryKey(autoGenerate = true) val pictureID: Int,
    @ColumnInfo(name = "blob", typeAffinity = ColumnInfo.BLOB) val blob: ByteArray,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "location") val location: String?,
    @ColumnInfo(name = "favorite") val favorite: Boolean
){
    @Ignore
    constructor(blob: ByteArray, title: String?, description: String?, location: String?):
            this(0, blob, title, description, location, false)
}
