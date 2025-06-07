package brsenas.com.github.aluno_rm552250.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import brsenas.com.github.aluno_rm552250.database.ItemDao
import brsenas.com.github.aluno_rm552250.database.ItemDatabase
import brsenas.com.github.aluno_rm552250.model.ItemModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ItemsViewModel(application: Application) : AndroidViewModel(application) {

    private val itemDao: ItemDao
    val itemsLiveData: LiveData<List<ItemModel>>

    init {
        val database = Room.databaseBuilder(
            getApplication(),
            ItemDatabase::class.java,
            "items_database"
        ).build()

        itemDao = database.itemDao()
        itemsLiveData = itemDao.getAll()
    }

    fun addItem(item: ItemModel) {
        viewModelScope.launch(Dispatchers.IO) {
            itemDao.insert(item)

        }
    }

    fun removeItem(item: ItemModel) {
        viewModelScope.launch(Dispatchers.IO) {
            itemDao.delete(item)
        }

    }
}