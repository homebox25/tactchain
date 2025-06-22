// db/AppDatabase.kt
package com.tactchain.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tactchain.model.Asset
import com.tactchain.dao.AssetDao

@Database(entities = [Asset::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun assetDao(): AssetDao
}