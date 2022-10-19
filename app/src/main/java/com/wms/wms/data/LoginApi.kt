package com.wms.wms.data

import com.wms.wms.data.model.LoginResponse
import com.wms.wms.data.model.Result

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginApi(val dataSource: LoginDataSource) {
    suspend fun login(baseUrl: String, username: String, password: String): Result<LoginResponse>? {
        // handle login
        val result = dataSource.login(baseUrl, username, password)

        if (result is Result.Success) {
            UserManager.login(result.data.username, result.data.accessToken, 1000 * 60 * 60)
        }

        return result
    }
}