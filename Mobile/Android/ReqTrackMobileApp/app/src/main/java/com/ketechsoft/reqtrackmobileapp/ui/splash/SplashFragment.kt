package com.ketechsoft.reqtrackmobileapp.ui.splash

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.ketechsoft.reqtrackmobileapp.R


class SplashFragment : Fragment(R.layout.fragment_splash) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        object : CountDownTimer(2000, 1000) {
            override fun onFinish() {
                Navigation.findNavController(view)
                    .navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
            }

            override fun onTick(millisUntilFinished: Long) {
            }

        }.start()
    }

}
