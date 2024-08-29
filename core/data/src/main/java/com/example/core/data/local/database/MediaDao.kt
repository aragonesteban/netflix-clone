package com.example.core.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.data.local.model.MediaDetailEntity
import com.example.core.data.local.model.MediaEntity

@Dao
interface MediaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMediaListItems(mediaList: List<MediaEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMediaDetail(mediaDetail: MediaDetailEntity)

    @Query("SELECT * FROM media_item")
    fun getMediaList(): List<MediaEntity>

    @Query("SELECT * FROM media_detail WHERE id = :mediaId")
    fun getMediaDetailById(mediaId: Int): MediaDetailEntity?
}