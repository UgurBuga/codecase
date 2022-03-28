package com.ugurbuga.codecase.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ugurbuga.codecase.data.entity.ProductEntity

@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
abstract class RoomDatabase : RoomDatabase() {

    abstract fun getProductDao(): ProductDao
}