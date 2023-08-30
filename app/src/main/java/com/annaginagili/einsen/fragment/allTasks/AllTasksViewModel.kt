package com.annaginagili.einsen.fragment.allTasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.annaginagili.einsen.data.Task
import com.annaginagili.einsen.dataImpl.TaskDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AllTasksViewModel: ViewModel() {
    private var tasks = MutableLiveData<List<Task>>()

    fun getTasks(taskDao: TaskDao) {
        CoroutineScope(Dispatchers.Default).launch {
            val result = taskDao.getAll()
            tasks.postValue(result)
        }
    }

    fun observeTasks(): LiveData<List<Task>> {
        return tasks
    }
}