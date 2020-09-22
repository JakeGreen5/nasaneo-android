package com.example.nasaneo.domain.model

import com.example.nasaneo.data.model.Feed
import javax.inject.Inject

class FeedMapper @Inject constructor(private val neoMapper: NeoMapper) :
    BaseMapper<Feed, FeedDomain> {
    override fun map(from: Feed): FeedDomain {
        val newMap = mutableMapOf<String, List<NeoDomain>>()
        from.nearEarthObjects.forEach {
            newMap[it.key] = it.value.map(neoMapper::map)
        }
        return FeedDomain(newMap)
    }
}