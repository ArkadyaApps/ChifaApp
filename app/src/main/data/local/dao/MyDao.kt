package com.arkadya.chifa.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.arkadya.chifa.data.local.entities.MyEntity

@Dao
interface MyDao {
    // ... your existing code ...
}

// In data/local/entities/MyEntity.kt
package com.arkadya.chifa.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_entity_table")
data class MyEntity(
    // ... your existing code ...
)