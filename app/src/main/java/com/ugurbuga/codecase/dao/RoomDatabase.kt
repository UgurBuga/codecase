package com.ugurbuga.codecase.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ugurbuga.codecase.domain.ProductUIModel

@Database(entities = [ProductUIModel::class], version = 1, exportSchema = false)
abstract class RoomDatabase : RoomDatabase() {

    abstract fun getProductDao(): ProductDao
}