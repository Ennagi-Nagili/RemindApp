package com.annaginagili.einsen.dataImpl

import android.icu.text.CaseMap.Title
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.annaginagili.einsen.data.Task

@Dao
interface TaskDao {
    @Query("Select * from Task")
    fun getAll(): List<Task>

    @Insert
    fun addTask(vararg task: Task)

    @Delete
    fun delete(task: Task)

    @Update
    fun edit(task: Task)
}