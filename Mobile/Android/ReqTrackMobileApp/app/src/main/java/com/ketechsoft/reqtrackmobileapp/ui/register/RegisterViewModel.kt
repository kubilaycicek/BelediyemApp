package com.ketechsoft.reqtrackmobileapp.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ketechsoft.reqtrackmobileapp.model.Register
import com.ketechsoft.reqtrackmobileapp.model.RegisterResponse
import com.ketechsoft.reqtrackmobileapp.network.Service
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
║     03/05/2020 - 17:51     ║
╚════════════════════════════╝
 */
class RegisterViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val success = MutableLiveData<RegisterResponse>()
    val error = MutableLiveData<Throwable>()
    val pbLoading = MutableLiveData<Boolean>()

    fun postRegister(register: Register) {
        compositeDisposable.add(
            Service().retrofit.register(register).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(object :
                    DisposableSingleObserver<RegisterResponse>() {
                    override fun onSuccess(t: RegisterResponse) {
                        success.value = t
                        pbLoading.value = false
                    }

                    override fun onError(e: Throwable) {
                        error.value = e
                        pbLoading.value = true
                    }

                })
        )
    }
}