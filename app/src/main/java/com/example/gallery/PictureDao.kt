package com.example.gallery

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PictureDao {
    @Query("SELECT * FROM picture")
    fun getAll(): List<Picture>
    @Query("SELECT * FROM picture WHERE pictureID IN (:pictureIds)")
    fun loadAllByIds(pictureIds: IntArray): List<Picture>
    @Insert
    fun insertAll(vararg pictures: Picture)
    @Delete
    fun delete(picture: Picture)
}
