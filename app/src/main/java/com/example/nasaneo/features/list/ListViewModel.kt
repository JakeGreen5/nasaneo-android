package com.example.nasaneo.features.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nasaneo.domain.model.Event
import com.example.nasaneo.domain.usecases.FetchNeosUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel @Inject constructor(
    private val fetchNeosUseCase: FetchNeosUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val itemToOpen = MutableLiveData<Event<ListItemState>>()
    val viewState = MutableLiveData<ListViewState>()

    init {
        compositeDisposable.add(fetchNeosUseCase.getFeed()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    viewState.value = ListViewState(it.map { neo ->
                        with(neo) {
                            ListItemState(this, name, url)
                        }
                    })
                }, {

                }
            ))
    }

    fun onItemClicked(itemState: ListItemState) {
        itemToOpen.value = Event(itemState)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
