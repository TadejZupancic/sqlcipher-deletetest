package com.example.deletetest

import androidx.room.*

@Entity
class Item(@field:PrimaryKey
           var id: Int = 0, var text: String = "")