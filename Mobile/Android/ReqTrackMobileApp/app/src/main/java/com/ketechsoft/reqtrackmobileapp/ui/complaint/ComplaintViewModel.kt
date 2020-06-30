package com.ketechsoft.reqtrackmobileapp.ui.complaint

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ketechsoft.reqtrackmobileapp.model.Complaint
import com.ketechsoft.reqtrackmobileapp.network.Service
import com.ketechsoft.reqtrackmobileapp.util.getID
import com.ketechsoft.reqtrackmobileapp.util.getToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ComplaintViewModel : ViewModel() {
    val complaintData = MutableLiveData<Complaint>()


    fun getData(requireContext: Context) {

        getID(requireContext)?.let {
            getToken(requireContext)?.let { it1 ->
                Service().retrofit.complaint(
                    "Bearer $it1",
                    it
                ).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(
                    {
                        it?.let {
                            complaintData.value = it
                            print("EMRE$it")

                        }

                    },
                    {
                        print("EMRE$it")
                    })
            }
        }
    }
}


