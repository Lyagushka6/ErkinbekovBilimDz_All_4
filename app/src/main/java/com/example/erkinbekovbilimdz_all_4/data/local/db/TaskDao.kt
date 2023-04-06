package com.example.erkinbekovbilimdz_all_4.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.erkinbekovbilimdz_all_4.model.Task


@Dao
interface TaskDao {

    @Delete
    fun delete (task: Task)

    @Insert
    fun insert (task : Task)

    //ASC - по алфавиту
    //DESC - сортировка сверху вниз

    @Query("SELECT * FROM task ORDER BY id DESC")
    fun getAll(): List<Task>
}