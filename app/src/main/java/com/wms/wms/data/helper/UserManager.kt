package com.wms.wms.data

import android.content.Context
import com.wms.wms.data.helper.EventManager
import com.wms.wms.data.helper.PreferenceHelper
import com.wms.wms.data.model.User
import java.util.*

open class LoginEvent(val type: String)

interface LoginEventListener<T : LoginEvent> {
    fun handle(event: T): Unit
}


class UserManager {
    companion object {
        private var eventManager = EventManager()

        private class LogoutLoginEvent : LoginEvent("logout")
        private class LoginLoginEvent : LoginEvent("login")

        private var user: User? = null

        fun initilize() {
             PreferenceHelper.getObject<User>( "User", null)?.let {

                if (!it.username.isNullOrEmpty() && !it.accessToken.isNullOrEmpty()) {
                    user = it
                }
            }
        }

        fun get(): User? {
            val current = Calendar.getInstance().time

            if (user !== null && user!!.expireAt <= current.time) {
                logout();
            }

            return user;
        }

        fun logout() {
            user = null;
            eventManager.notify(LogoutLoginEvent())

            PreferenceHelper.setObject("User", null)
        }

        fun login(
            username: String?,
            fullname: String,
            accessToken: String?,
            expireAt: Long,
            cookie: String
        ) {
            if (!username.isNullOrEmpty() && !accessToken.isNullOrEmpty()) {

                user = User(username,fullname, accessToken, expireAt, cookie)

                PreferenceHelper.setObject("User", user)

                eventManager.notify(LoginLoginEvent())
            }
        }


        fun addLoginListener(handle: () -> Unit) {
            eventManager.register(object : LoginEventListener<LoginLoginEvent> {
                override fun handle(event: LoginLoginEvent) {
                    handle()
                }
            })
        }

        fun addLogoutListener(handle: () -> Unit) {
            eventManager.register(object : LoginEventListener<LogoutLoginEvent> {
                override fun handle(event: LogoutLoginEvent) {
                    handle()
                }
            })
        }

    }
}