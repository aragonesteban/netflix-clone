package com.example.core.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "media_item")
data class MediaEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val mediaId: Int,
    val posterPath: String,
    val title: String,
    val category: String,
    val mediaType: String
)