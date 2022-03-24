package com.ugurbuga.turkcellcodecase.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ugurbuga.turkcellcodecase.domain.ProductUIModel

@Database(entities = [ProductUIModel::class], version = 1, exportSchema = false)
abstract class RoomDatabase : RoomDatabase() {

    abstract fun getProductDao(): ProductDao
}