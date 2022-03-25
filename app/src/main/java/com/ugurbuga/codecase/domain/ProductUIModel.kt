package com.ugurbuga.codecase.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productTable")
data class ProductUIModel(

    @PrimaryKey
    @ColumnInfo(name = "productId")
    val productId: String,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "price")
    val price: String,
)