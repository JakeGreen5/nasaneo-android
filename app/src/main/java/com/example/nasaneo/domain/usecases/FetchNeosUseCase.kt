package com.example.nasaneo.domain.usecases

import com.example.nasaneo.data.NeoRepository
import com.example.nasaneo.data.model.Neo
import io.reactivex.Single
import javax.inject.Inject

class FetchNeosUseCase @Inject constructor(
    private val neoRepository: NeoRepository
) {

    fun getFeed(): Single<List<Neo>> {
        return neoRepository.getFeed().map {
            it.nearEarthObjects.entries.first().value
        }
    }

}