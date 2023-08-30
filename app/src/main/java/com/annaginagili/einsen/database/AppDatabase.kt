package com.annaginagili.einsen.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.annaginagili.einsen.data.Task
import com.annaginagili.einsen.dataImpl.TaskDao

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
}