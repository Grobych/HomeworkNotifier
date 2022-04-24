package com.globa.homeworknotifier.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity
data  class Task(
    @PrimaryKey
    val id : Long,
    val title : String,
    val description : String,
    val deadline : Date,
    val subject: Subject
)