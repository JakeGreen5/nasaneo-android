package com.example.nasaneo.features.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nasaneo.domain.model.Event
import com.example.nasaneo.domain.model.FeedDomain
import com.example.nasaneo.domain.model.FeedUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel @Inject constructor(
    // feel free to inject required dependencies here
    private val feedUseCase: FeedUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val itemToOpen = MutableLiveData<Event<ListItemState>>()
    val viewState = MutableLiveData<ListViewState>()

    init {
        // put your code fetching data here
    }

    fun getFeed() {
        feedUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onFeedFetched, ::onFeedError).toDisposable()
    }

    fun onFeedFetched(feedDomain: FeedDomain) {
//        feedDomain.nearEarthObjects.
    }

    fun onFeedError(throwable: Throwable) {

    }

    fun onItemClicked(itemState: ListItemState) {
        itemToOpen.value = Event(itemState)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    private fun Disposable.toDisposable() = compositeDisposable.add(this)
}
