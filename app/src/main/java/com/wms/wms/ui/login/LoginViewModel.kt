package com.wms.wms.ui.login

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wms.wms.R
import com.wms.wms.data.api.login.LoginApi
import com.wms.wms.data.api.login.LoginDataSource
import com.wms.wms.data.model.response.ApiResult

class LoginViewModel(private val application: Application) : ViewModel() {
    private val loginApi = LoginApi(
        dataSource = LoginDataSource()
    )

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    suspend fun login(username: String, password: String) {
        // can be launched in a separate asynchronous job
        val result = loginApi.login(username, password)



        if (result.isSuccessful && result.data !== null) {
            _loginResult.value = LoginResult(
                success = LoggedInUserView(
                    result.data!!.username,
                    result.data!!.fullname,
                    result.data!!.accessToken
                )
            )
        } else {
            _loginResult.value = LoginResult(
                error = result.errorBody?.message
                    ?: application.resources.getString(R.string.login_failed)
            )
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return username.isNotBlank()
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length >= 6 && password.isNotBlank()
    }
}