package com.arkadya.chifa.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_entity_table")
data class MyEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String
)