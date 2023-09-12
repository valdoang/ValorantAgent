package com.valdoang.valorantagent.core.data.source.local.room

import androidx.room.*
import com.valdoang.valorantagent.core.data.source.local.entity.ValorantEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ValorantDao {

    @Query("SELECT * FROM valorant")
    fun getAllAgent(): Flow<List<ValorantEntity>>

    @Query("SELECT * FROM valorant where isFavorite = 1")
    fun getFavoriteAgent(): Flow<List<ValorantEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAgent(valorant: List<ValorantEntity>)

    @Update
    fun updateFavoriteAgent(valorant: ValorantEntity)

}