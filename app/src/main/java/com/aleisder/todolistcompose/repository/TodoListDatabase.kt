package com.aleisder.todolistcompose.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aleisder.todolistcompose.model.Todo

@Database(
    entities = [Todo::class],
    version = 3
)
abstract class TodoListDatabase : RoomDatabase() {

    abstract fun getTodoListDao(): TodoListDao

    companion object {

        private var INSTANCE: TodoListDatabase? = null

        fun getInstance(context: Context): TodoListDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context = context,
                    klass = TodoListDatabase::class.java,
                    name = "todolist.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as TodoListDatabase
        }
    }


}