package com.valdoang.valorantagent.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "valorant")
data class ValorantEntity(
    @PrimaryKey
    @ColumnInfo(name = "agentId")
    var agentId: String,

    @ColumnInfo(name = "agentName")
    var agentName: String,

    @ColumnInfo(name = "agentDescription")
    var agentDescription: String,

    @ColumnInfo(name = "agentIcon")
    var agentIcon: String,

    @ColumnInfo(name = "agentPortrait")
    var agentPortrait: String?,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)