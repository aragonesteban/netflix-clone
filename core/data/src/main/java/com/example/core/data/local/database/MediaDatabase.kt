package com.example.core.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.local.model.MediaDetailEntity
import com.example.core.data.local.model.MediaEntity

@Database(
    entities = [MediaEntity::class, MediaDetailEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MediaDatabase : RoomDatabase() {
    abstract fun moviesDao(): MediaDao
}