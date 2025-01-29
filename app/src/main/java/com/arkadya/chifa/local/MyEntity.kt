package com.arkadya.chifa.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_entity_table")
data class MyEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)