package com.annaginagili.einsen.fragment.addFragment

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.annaginagili.einsen.data.Task
import com.annaginagili.einsen.dataImpl.TaskDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddFragmentViewModel: ViewModel() {
    fun addTask(taskDao: TaskDao, task: Task) {
        CoroutineScope(Dispatchers.Default).launch {
            taskDao.addTask(task)
        }
    }
}