package com.ketechsoft.reqtrackmobileapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.ketechsoft.reqtrackmobileapp.R
import com.ketechsoft.reqtrackmobileapp.network.Service
import com.ketechsoft.reqtrackmobileapp.util.getID
import com.ketechsoft.reqtrackmobileapp.util.getToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getToken(requireContext())?.let {
            getID(requireContext())?.let { it2 ->
                Service().retrofit.profile("Bearer $it", it2)
                    .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                    .subscribe({
                        it?.let {
                            prTxtTcNumber.setText(it.tcNumber)
                            prTxtEmail.setText(it.email)
                            prTxtName.setText(it.name)
                            prTxtSurname.setText(it.surname)
                            prTxtPhoneNumber.setText(it.phone)
                        }
                    })

            }
        }
        prBtnBack.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(ProfileFragmentDirections.actionProfileFragmentToHomeFragment())
        }
    }
}
