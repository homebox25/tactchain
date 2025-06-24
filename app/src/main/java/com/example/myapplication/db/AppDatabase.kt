// db/AppDatabase.kt
package com.tactchain.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tactchain.dao.AssetDao
import com.tactchain.model.Asset

@Database(entities = [Asset::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun assetDao(): AssetDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "asset_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}