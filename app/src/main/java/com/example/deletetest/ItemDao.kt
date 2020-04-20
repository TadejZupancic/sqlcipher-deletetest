package com.example.deletetest

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
abstract class ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(item: Item)

    @Query("DELETE FROM item WHERE id IN (:ids)")
    abstract fun deleteAll(ids: List<Int>)
}
