package com.annaginagili.einsen.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Task(@PrimaryKey(autoGenerate = true) val id: Int,
                @ColumnInfo(name = "title") val title: String,
                @ColumnInfo(name = "description") val description: String,
                @ColumnInfo(name = "category") val category: String,
                @ColumnInfo(name = "importance") val importance: String,
                @ColumnInfo(name = "urgency") val urgency: String): Serializable