package com.example.deletetest

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1, exportSchema = true)
abstract class TestDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}