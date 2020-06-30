package com.ketechsoft.reqtrackmobileapp.ui.register

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ketechsoft.reqtrackmobileapp.R
import com.ketechsoft.reqtrackmobileapp.databinding.FragmentRegisterBinding
import com.ketechsoft.reqtrackmobileapp.model.Register
import com.ketechsoft.reqtrackmobileapp.util.isEmailValid
import com.ketechsoft.reqtrackmobileapp.util.isOnline
import com.ketechsoft.reqtrackmobileapp.util.isPhoneValid
import kotlinx.android.synthetic.main.custompb.*
import kotlinx.android.synthetic.main.fragment_register.*


class RegisterFragment : Fragment(R.layout.fragment_register) {

    private var fragmentRegisterBinding: FragmentRegisterBinding? = null
    private lateinit var registerViewModel: RegisterViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        val binding = FragmentRegisterBinding.bind(view)
        fragmentRegisterBinding = binding


        binding.rbBtnRegister.setOnClickListener {

            if (checkFormValidation()) {

                if (isOnline(requireContext())) {
                    registerProgressBar.visibility = View.VISIBLE
                    val register = Register(
                        binding.rgTxtName.text.toString(),
                        binding.rgTxtSurname.text.toString(),
                        binding.rgTxtTcNumber.text.toString(),
                        binding.rgTxtPassword.text.toString(),
                        binding.rgTxtEmail.text.toString(),
                        binding.rgTxtPhoneNumber.text.toString()
                    )
                    registerViewModel.postRegister(register)
                    observeLiveData(view)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Lütfen İnternet Bağlantınızı Kontrol Ediniz",
                        Toast.LENGTH_SHORT
                    ).show()
                }


            }


        }

    }

    private fun observeLiveData(view: View) {
        registerViewModel.success.observe(viewLifecycleOwner, Observer { response ->
            response?.let {
                if (it.success) {
                    Toast.makeText(requireContext(), "BAŞARIYLA KAYIT OLDUNUZ", Toast.LENGTH_SHORT)
                        .show()
                    Navigation.findNavController(view)
                        .navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
                }
            }
        })
        registerViewModel.pbLoading.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) registerProgressBar.visibility = View.VISIBLE
                else registerProgressBar.visibility = View.GONE
            }
        })
        registerViewModel.error.observe(viewLifecycleOwner, Observer {
            it?.let {
                pbText.text = it.message
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                registerProgressBar.visibility = View.GONE
            }
        })
    }

    private fun checkFormValidation(): Boolean {
        var check = false
        if (rgTxtName.text.toString().isEmpty()) {
            Toast.makeText(requireContext(), "Lütfen İsminizi Giriniz!", Toast.LENGTH_SHORT).show()
            rgTxtName.error = "İsminizi Giriniz"
            check = false
        } else if (rgTxtSurname.text.toString().isEmpty()) {
            Toast.makeText(requireContext(), "Lütfen Soyadınızı Giriniz!", Toast.LENGTH_SHORT)
                .show()
            rgTxtSurname.error = "Soyadınızı Giriniz"
            check = false
        } else if (rgTxtTcNumber.text.toString().isEmpty()) {
            Toast.makeText(
                requireContext(),
                "Lütfen Kimlik Numarınızı Giriniz!",
                Toast.LENGTH_SHORT
            ).show()
            rgTxtTcNumber.error = "Kimlik Numarınızı Giriniz"
            check = false
        } else if (rgTxtTcNumber.text.toString().length < 11) {
            Toast.makeText(
                requireContext(),
                "Kimlik Numarınız 11 Haneden Oluşmalıdır!",
                Toast.LENGTH_SHORT
            ).show()
            rgTxtTcNumber.error = "Kimlik Numarınızı Eksik Girdiniz"
            check = false
        } else if (rgTxtTcNumber.text.toString().length > 11) {
            Toast.makeText(
                requireContext(),
                "Kimlik Numarınız 11 Haneden Oluşmalıdır!",
                Toast.LENGTH_SHORT
            ).show()
            rgTxtTcNumber.error = "Kimlik Numarınızı Fazla Girdiniz"
            check = false
        } else if (!isEmailValid(rgTxtEmail.text.toString())) {
            Toast.makeText(
                requireContext(),
                "Email Adresinizi Kontrol Ediniz",
                Toast.LENGTH_SHORT
            ).show()
            rgTxtEmail.error = "Email Adresinizi Kontrol Ediniz"
            check = false
        } else if (!isPhoneValid(rgTxtPhoneNumber.text.toString())) {
            Toast.makeText(
                requireContext(),
                "Telefon Numarınızı Kontrol Ediniz",
                Toast.LENGTH_SHORT
            ).show()
            rgTxtPhoneNumber.error = "Telefon Numarınızı Kontrol Ediniz"
            check = false
        } else if (rgTxtPassword.text.toString().isEmpty()) {
            Toast.makeText(
                requireContext(),
                "Şifrenizi Giriniz!",
                Toast.LENGTH_SHORT
            ).show()
            rgTxtPassword.error = "Şifrenizi Giriniz!"
            check = false
        } else if (rgTxtPasswordAgain.text.toString().isEmpty()) {
            Toast.makeText(
                requireContext(),
                "Şifrenizi Tekrar Giriniz!",
                Toast.LENGTH_SHORT
            ).show()
            rgTxtPasswordAgain.error = "Şifrenizi Tekrar Giriniz!"
            check = false
        } else if (rgTxtPassword.text.toString() != rgTxtPasswordAgain.text.toString()) {
            Toast.makeText(
                requireContext(),
                "Şifreleriniz Uyuşmuyor Kontrol Ediniz!",
                Toast.LENGTH_SHORT
            ).show()
            rgTxtPasswordAgain.error = "Şifreleriniz Uyuşmuyor Kontrol Ediniz!"
            check = false
        } else {
            check = true
        }
        return check

    }

    override fun onDestroy() {
        fragmentRegisterBinding = null
        super.onDestroy()
    }


}
