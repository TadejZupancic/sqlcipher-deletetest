package com.example.deletetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import net.sqlcipher.database.SupportFactory

class MainActivity : AppCompatActivity() {

    private var testDb: TestDatabase? = null
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val key = "password"
        val factory = SupportFactory(key.toByteArray())

        testDb = Room.databaseBuilder(applicationContext, TestDatabase::class.java, "test.db")
                .openHelperFactory(factory) // works without a problem if you comment out this line
                .build()

        scope.launch {
            for (i in 1..100) {
                Log.d("delete test", "$i")
                testInsert()
                testDb?.itemDao()?.deleteAll(listOf(111, 112)) // ids here don't matter, can be even empty and it still produces a problem
            }
        }
    }

    private fun testInsert() {
        for (i in 1..100) {
            testDb?.itemDao()?.insert(Item(i))
        }
    }
}