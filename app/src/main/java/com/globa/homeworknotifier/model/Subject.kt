package com.globa.homeworknotifier.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Subject(
    @PrimaryKey
    val name : String
)