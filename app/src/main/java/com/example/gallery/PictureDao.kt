package com.example.gallery

import androidx.room.Dao
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
    @Query("DELETE FROM picture WHERE pictureID = :pictureID")
    fun delete(pictureID: Int)
    @Query("UPDATE picture SET favorite = :favorite WHERE pictureID = :pictureId")
    fun updateFavorite(pictureId: Int, favorite: Boolean)
}
