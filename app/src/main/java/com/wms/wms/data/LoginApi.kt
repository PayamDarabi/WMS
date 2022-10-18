package com.wms.wms.data

import android.content.Context
import com.wms.wms.data.model.Result
import com.wms.wms.data.model.LoginResponse

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginApi(val dataSource: LoginDataSource) {

    // in-memory cache of the User object
    var loginResponse: LoginResponse? = null
        private set

    val isLoggedIn: Boolean
        get() = loginResponse != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        loginResponse = null
    }

    fun logout() {
        loginResponse = null
        dataSource.logout()
    }

    suspend fun login(baseUrl: String, username: String, password: String): Result<LoginResponse>? {
        // handle login
        val result = dataSource.login(baseUrl,username, password)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

    private fun setLoggedInUser(loggedInLoginResponse: LoginResponse) {
        this.loginResponse = loggedInLoginResponse
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}