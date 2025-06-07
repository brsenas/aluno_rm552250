package brsenas.com.github.aluno_rm552250.database

import androidx.room.Database
import androidx.room.RoomDatabase
import brsenas.com.github.aluno_rm552250.model.ItemModel

@Database(entities = [ItemModel::class], version = 2)
abstract class ItemDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao
}