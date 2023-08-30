package com.annaginagili.einsen.fragment.detailsFragment

import androidx.lifecycle.ViewModel
import com.annaginagili.einsen.data.Task
import com.annaginagili.einsen.dataImpl.TaskDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsFragmentViewModel: ViewModel() {
    fun deleteTask(taskDao: TaskDao, task: Task) {
        CoroutineScope(Dispatchers.Default).launch {
            taskDao.delete(task)
        }
    }
}