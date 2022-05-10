package com.globa.homeworknotifier.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data  class Task(
    @PrimaryKey
    var id : Long?,
    var title : String,
    var description : String,
    var deadline : Date,
    var status: TaskStatus,
    @Embedded
    var subject: Subject?
)