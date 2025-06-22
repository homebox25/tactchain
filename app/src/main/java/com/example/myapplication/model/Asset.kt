// model/Asset.kt
package com.tactchain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Asset(
    @PrimaryKey val assetId: String,
    var status: String,
    val lastUpdated: Long = System.currentTimeMillis()
)