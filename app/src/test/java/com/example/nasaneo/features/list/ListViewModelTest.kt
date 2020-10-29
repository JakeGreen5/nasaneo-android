package com.example.nasaneo.features.list

import android.os.Handler
import android.os.Looper
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nasaneo.data.model.Neo
import com.example.nasaneo.domain.usecase.GetFeedUseCase
import com.google.common.truth.ExpectFailure
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.`when`

class ListViewModelTest {

    private val neo1 = Neo("id1", "refid", "name", "url")
    private val neo2 = Neo("id2", "refid", "name", "url")
    private val neo3 = Neo("id3", "refid", "name", "url")
    private val neo4 = Neo("id4", "refid", "name", "url")

    private val getFeedUseCase = mock<GetFeedUseCase>()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun `should fetch items`() {
        // given
        `when`(getFeedUseCase.build()).thenReturn(Single.just(listOf(neo1, neo2, neo3, neo4)))

        // when
        val viewModel = createViewModel()

        // then
        Assert.assertEquals(4, viewModel.viewState.value?.items?.size)
    }

    private fun createViewModel() =
        ListViewModel(getFeedUseCase)
}
