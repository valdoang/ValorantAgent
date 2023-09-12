package com.valdoang.valorantagent.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Valorant(
    val agentId: String,
    val agentName: String,
    val agentDescription: String,
    val agentIcon: String,
    val agentPortrait: String?,
    val isFavorite: Boolean
) : Parcelable