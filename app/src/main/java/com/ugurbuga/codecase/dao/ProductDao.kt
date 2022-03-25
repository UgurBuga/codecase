package com.ugurbuga.codecase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ugurbuga.codecase.domain.ProductUIModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(list: ProductUIModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<ProductUIModel>)

    @Query("Select * from productTable")
    fun getProductList(): Flow<List<ProductUIModel>>

}