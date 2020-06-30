package com.ketechsoft.reqtrackmobileapp.ui.newcomplaint

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ketechsoft.reqtrackmobileapp.model.NewComplaint
import com.ketechsoft.reqtrackmobileapp.network.Service
import com.ketechsoft.reqtrackmobileapp.util.getToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


/**     Code with ❤
╔════════════════════════════╗
║  Created by Emre GÖREN ║
╠════════════════════════════╣
║ emregrnn58@gmail.com             ║
╠════════════════════════════╣
║     14/05/2020 - 00:55     ║
╚════════════════════════════╝
 */
class NewComplaintViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val complaint = MutableLiveData<NewComplaint>()
    val nwLoading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Throwable>()

    fun postComplaint(context: Context, newComplaint: NewComplaint) {


        getToken(context)?.let {
            compositeDisposable.add(
                Service().retrofit.newComplaint("Bearer $it", newComplaint)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<NewComplaint>() {
                        override fun onSuccess(t: NewComplaint) {
                            complaint.value = t
                            nwLoading.value = false
                        }

                        override fun onError(e: Throwable) {
                            error.value = e
                            nwLoading.value = true

                        }

                    })
            )
        }

    }

    fun getList(context: Context) {
        getToken(context)?.let {
            Service().retrofit.getData("Bearer $it").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({
                print(it)
            },
                {
                    print(it)
                })
        }
    }
}