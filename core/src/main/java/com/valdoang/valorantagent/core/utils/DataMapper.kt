package com.valdoang.valorantagent.core.utils

import com.valdoang.valorantagent.core.data.source.local.entity.ValorantEntity
import com.valdoang.valorantagent.core.data.source.remote.response.ValorantResponse
import com.valdoang.valorantagent.core.domain.model.Valorant

object DataMapper {

    fun mapResponsesToEntities(input: List<ValorantResponse>):List<ValorantEntity>{
        val valorantList = ArrayList<ValorantEntity>()
        input.map {
            val valorant = ValorantEntity(it.uuid, it.displayName, it.description,it.displayIcon,
                it.fullPortrait
            )
            valorantList.add(valorant)
        }
        return valorantList
    }

    fun mapEntitiesToDomain(input: List<ValorantEntity>): List<Valorant> =
        input.map {
            Valorant(it.agentId, it.agentName, it.agentDescription, it.agentIcon,
                it.agentPortrait, it.isFavorite)
        }

    fun mapDomainToEntity(input: Valorant) = ValorantEntity(
        input.agentId, input.agentName, input.agentDescription, input.agentIcon, input.agentPortrait, input.isFavorite
    )
}