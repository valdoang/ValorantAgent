package com.valdoang.valorantagent.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.valdoang.valorantagent.core.data.source.local.entity.ValorantEntity

@Database(entities = [ValorantEntity::class], version = 2, exportSchema = false)
abstract class ValorantDatabase: RoomDatabase() {

    abstract fun valorantDao(): ValorantDao

}