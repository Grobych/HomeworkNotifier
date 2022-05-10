package com.globa.homeworknotifier.room

import androidx.room.*
import com.globa.homeworknotifier.model.Subject
import com.globa.homeworknotifier.model.Task
import com.globa.homeworknotifier.model.TaskStatus

@Dao
interface TaskDao {
    @Query("select * from task")
    fun getAll() : List<Task>
    @Query("select * from task where id = :id")
    fun get(id : Long) : Task
//    @Query("select * from task where subject like :subject")
//    fun get(subject: Subject) : List<Task>
    @Query("select * from task where status like :status")
    fun getByStatus(status: TaskStatus) : List<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task) : Long
    @Update
    fun update(task: Task)
    @Delete
    fun delete(task: Task)
}