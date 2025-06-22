// dao/AssetDao.kt
package com.tactchain.dao

import androidx.room.*
import com.tactchain.model.Asset

@Dao
interface AssetDao {
    @Query("SELECT * FROM Asset ORDER BY lastUpdated DESC")
    suspend fun getAll(): List<Asset>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(asset: Asset)

    @Delete
    suspend fun delete(asset: Asset)
}