package com.wms.wms.ui.login

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.wms.wms.R
import com.wms.wms.data.helper.PreferenceHelper
import com.wms.wms.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = binding.username
        val password = binding.password
        val tilPassword = binding.tilpassword
        val tilServerPath = binding.tilServerPath
        val serverPath = binding.serverPath
        val tvLogin = binding.tvLogin
        val login = binding.login
        val loading = binding.loading

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid
            if(loginState.isDataValid) {
                login.setBackgroundColor(getColor(R.color.yellow_500))
                login.setTextColor(getColor(R.color.white))
            }
            else{
                login.setBackgroundColor(getColor(R.color.gray_200))
                login.setTextColor(getColor(R.color.gray_700))
            }
            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                tilPassword.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
                //Complete and destroy login activity once successful
                finish()
            }
            setResult(Activity.RESULT_OK)

        })

        tvLogin.setOnClickListener { tilServerPath.visibility=View.VISIBLE }
        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                loading.visibility = View.VISIBLE
                PreferenceHelper.setString("BaseUrl", serverPath.text.toString())
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        lifecycleScope.launchWhenCreated {
                            loginViewModel.login(
                                username.text.toString(),
                                password.text.toString()
                            )
                        }
                }
                false
            }

            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                PreferenceHelper.setString("BaseUrl", serverPath.text.toString())
                lifecycleScope.launchWhenCreated {
                    loginViewModel.login(
                        username.text.toString(),
                        password.text.toString()
                    )
                }
            }
        }
    }


    private fun updateUiWithUser(model: LoggedInUserView) {
        // TODO : initiate successful logged in experience
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}