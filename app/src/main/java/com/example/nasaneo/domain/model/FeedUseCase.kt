package com.example.nasaneo.domain.model

import com.example.nasaneo.data.NeoRepository
import io.reactivex.Single
import javax.inject.Inject

class FeedUseCase @Inject constructor(
    private val neoRepository: NeoRepository,
    private val feedMapper: FeedMapper
) {
    fun execute(): Single<FeedDomain> =
        neoRepository.getFeed()
            .map(feedMapper::map)
}