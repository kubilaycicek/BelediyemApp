package com.ketechsoft.reqtrackmobileapp.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ketechsoft.reqtrackmobileapp.model.Login
import com.ketechsoft.reqtrackmobileapp.model.LoginResponse
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
║     02/05/2020 - 14:51     ║
╚════════════════════════════╝
 */

class LoginViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val user = MutableLiveData<LoginResponse>()
    val progressBar = MutableLiveData<Boolean>()
    val error = MutableLiveData<Throwable>()


    fun postLogin(loginUserModel: Login) {
        progressBar.value = true
        compositeDisposable.add(
            Service().retrofit.login(loginUserModel).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<LoginResponse>() {
                    override fun onSuccess(t: LoginResponse) {
                        user.value = t
                        progressBar.value = false
                    }

                    override fun onError(e: Throwable) {
                        error.value = e
                        progressBar.value = true
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}