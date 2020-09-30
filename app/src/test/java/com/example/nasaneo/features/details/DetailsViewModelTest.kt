package com.example.nasaneo.features.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nasaneo.data.model.Neo
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class DetailsViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val neo = Neo("id", "refid", "name", "url")

    @Test
    fun `should set view state`() {
        // when
        val detailsViewModel = DetailsViewModel(neo)

        // then
        assertThat(detailsViewModel.viewState.value?.name).isEqualTo("Name: ${neo.name}")
    }

}
