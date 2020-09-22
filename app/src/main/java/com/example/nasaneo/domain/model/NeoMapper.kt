package com.example.nasaneo.domain.model

import com.example.nasaneo.data.model.Neo
import javax.inject.Inject

class NeoMapper @Inject constructor() : BaseMapper<Neo, NeoDomain> {
    override fun map(from: Neo): NeoDomain = NeoDomain(
        id = from.id,
        refId = from.refId,
        name = from.url,
        url = from.name
    )
}