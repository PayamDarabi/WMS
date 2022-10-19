package com.wms.wms.data

import android.content.Context
import java.util.*
import kotlin.reflect.KClass


class User(
    public var username: String,
    public var accessToken: String,
    public var expireAt: Long,
) {}


open class Event(val id: String)

interface EventListener<T : Event> {
    fun handle(event: T): Unit
}

class EventManager {
    val listeners: MutableMap<KClass<*>, MutableList<EventListener<out Event>>> = mutableMapOf()


    inline fun <reified T : Event> register(listener: EventListener<T>) {
        val eventClass = T::class
        val eventListeners: MutableList<EventListener<out Event>> =
            listeners.getOrPut(eventClass) { mutableListOf() }
        eventListeners.add(listener)
    }

    inline fun <reified T : Event> notify(event: T) {
        // here we have an unsafe action of going from unknown EventListener<T: Event> to EventListener<R>
        // which we know it is because of our code logic, but the compiler cannot know this for sure
        listeners[event::class]?.asSequence()
            ?.filterIsInstance<EventListener<T>>() // or cast each item in a map() call
            ?.forEach { it.handle(event) }
    }

    // or if you don't know the event type, this is also very unsafe
    fun notifyUnknown(event: Event) {
        listeners[event::class]?.asSequence()
            ?.filterIsInstance<EventListener<Event>>()
            ?.forEach { it.handle(event) }
    }
}


class UserManager {
    companion object {
        private var applicationContext: Context? = null
        private var eventManager = EventManager()

        private class LogoutEvent : Event("logout")
        private class LoginEvent : Event("login")

        private var user: User? = null

        fun initilize(context: Context) {
            applicationContext = context;

            var sharedPreferences =
                context.getSharedPreferences("UserManager", Context.MODE_PRIVATE);
            var username = sharedPreferences.getString("username", null);
            var accessToken = sharedPreferences.getString("accessToken", null);
            var expireAt = sharedPreferences.getLong("expireAt", 0);

            if (!username.isNullOrEmpty() && !accessToken.isNullOrEmpty()) {
                user = User(username, accessToken, expireAt)
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
            eventManager.notify(LogoutEvent())

            var sharedPreferences =
                applicationContext!!.getSharedPreferences("UserManager", Context.MODE_PRIVATE);
            var edit = sharedPreferences.edit()
            edit.putString("username", null)
            edit.putString("accessToken", null)
            edit.putLong("expireAt", 0)
            edit.apply()
        }

        fun login(
            username: String?,
            accessToken: String?,
            expireAt: Long,
        ) {
            if (!username.isNullOrEmpty() && !accessToken.isNullOrEmpty()) {

                var sharedPreferences =
                    applicationContext!!.getSharedPreferences("UserManager", Context.MODE_PRIVATE);
                var edit = sharedPreferences.edit()
                edit.putString("username", username)
                edit.putString("accessToken", accessToken)
                edit.putLong("expireAt", expireAt)
                edit.apply()

                eventManager.notify(LoginEvent())
                user = User(username, accessToken, expireAt)
            }
        }


        fun addLoginListener(handle: () -> Unit) {
            eventManager.register(object : EventListener<LoginEvent> {
                override fun handle(event: LoginEvent) {
                    handle()
                }
            })
        }

        fun addLogoutListener(handle: () -> Unit) {
            eventManager.register(object : EventListener<LogoutEvent> {
                override fun handle(event: LogoutEvent) {
                    handle()
                }
            })
        }
    }
}