package com.example.deprem.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.deprem.model.depremListRespose
import com.example.deprem.service.DepremApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class DepremViewModel : ViewModel() {

    private val depremApiService = DepremApiService()
    private val disposable = CompositeDisposable()
    val getDepremListLiveData = MutableLiveData<depremListRespose>()

    fun refreshData() {
        getDepremData()
    }

    fun getDepremData() {
        disposable.add(
            depremApiService.getDeprem()
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<depremListRespose>() {
                    override fun onSuccess(t : depremListRespose) {
                        getDepremListLiveData.value = t
                    }
                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }
}

