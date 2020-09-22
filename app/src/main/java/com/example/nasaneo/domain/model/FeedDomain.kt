package com.example.nasaneo.domain.model

data class FeedDomain(
    val nearEarthObjects: Map<String, List<NeoDomain>>
)