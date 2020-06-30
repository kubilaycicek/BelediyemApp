package com.ketechsoft.reqtrackmobileapp.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ketechsoft.reqtrackmobileapp.R
import com.ketechsoft.reqtrackmobileapp.databinding.FragmentLoginBinding
import com.ketechsoft.reqtrackmobileapp.model.Login
import com.ketechsoft.reqtrackmobileapp.util.isOnline
import com.ketechsoft.reqtrackmobileapp.util.saveID
import com.ketechsoft.reqtrackmobileapp.util.saveToken
import kotlinx.android.synthetic.main.custompb.*
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment(R.layout.fragment_login) {
    private var fragmentLoginBinding: FragmentLoginBinding? = null
    lateinit var viewModel: LoginViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        val binding = FragmentLoginBinding.bind(view)
        fragmentLoginBinding = binding

        loginProgressBar.visibility = View.GONE
        binding.btnLogin.setOnClickListener {

            if (checkFormValidation()) {
                if (isOnline(requireContext())) {
                    btnLogin.visibility = View.GONE
                    btnRegister.visibility = View.GONE
                    loginProgressBar.visibility = View.VISIBLE
                    val login = Login(
                        binding.edtTxtLytTcNo.editText?.text.toString(),
                        binding.textInputLayout.editText?.text.toString()
                    )
                    viewModel.postLogin(login)
                    observeData(view)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Lütfen İnternet Bağlantınızı Kontrol Ediniz",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }


        }

        binding.btnRegister.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }

    }

    private fun checkFormValidation(): Boolean {
        var check = false
        if (edtPassword.text.toString().isEmpty()) {
            Toast.makeText(requireContext(), "Lütfen Şifrenizi Giriniz", Toast.LENGTH_SHORT).show()
            edtPassword.error = "Şifrenizi Giriniz"
            check = false
        } else if (edtTcNo.text.toString().isEmpty()) {
            Toast.makeText(
                requireContext(),
                "Lütfen Kimlik Numarınızı Giriniz!",
                Toast.LENGTH_SHORT
            ).show()
            edtTcNo.error = "Kimlik Numarınızı Giriniz"
            check = false
        } else if (edtTcNo.text.toString().length < 11) {
            Toast.makeText(
                requireContext(),
                "Kimlik Numarınız 11 Haneden Oluşmalıdır!",
                Toast.LENGTH_SHORT
            ).show()
            edtTcNo.error = "Kimlik Numarınızı Eksik Girdiniz"
            check = false
        } else if (edtTcNo.text.toString().length > 11) {
            Toast.makeText(
                requireContext(),
                "Kimlik Numarınız 11 Haneden Oluşmalıdır!",
                Toast.LENGTH_SHORT
            ).show()
            edtTcNo.error = "Kimlik Numarınızı Fazla Girdiniz"
            check = false
        } else {
            check = true
        }
        return check

    }

    fun observeData(view: View) {
        viewModel.user.observe(viewLifecycleOwner, Observer { succes ->
            succes.let {
                saveToken(requireContext(), it.token)
                saveID(requireContext(), it.id)
                Navigation.findNavController(view)
                    .navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
            }

        })

        viewModel.error.observe(viewLifecycleOwner, Observer {
            it?.let {
                pbText.text = it.message
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                loginProgressBar.visibility = View.GONE
                btnLogin.visibility = View.VISIBLE
                btnRegister.visibility = View.VISIBLE
            }
        })
    }


}
