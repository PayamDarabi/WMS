package com.wms.wms.data.helper

import com.wms.wms.data.LoginEvent
import com.wms.wms.data.LoginEventListener
import kotlin.reflect.KClass

class EventManager {
    val listeners: MutableMap<KClass<*>, MutableList<LoginEventListener<out LoginEvent>>> = mutableMapOf()


    inline fun <reified T : LoginEvent> register(listener: LoginEventListener<T>) {
        val eventClass = T::class
        val loginLoginEventListeners: MutableList<LoginEventListener<out LoginEvent>> =
            listeners.getOrPut(eventClass) { mutableListOf() }
        loginLoginEventListeners.add(listener)
    }

    inline fun <reified T : LoginEvent> notify(event: T) {
        // here we have an unsafe action of going from unknown EventListener<T: Event> to EventListener<R>
        // which we know it is because of our code logic, but the compiler cannot know this for sure
        listeners[event::class]?.asSequence()
            ?.filterIsInstance<LoginEventListener<T>>() // or cast each item in a map() call
            ?.forEach { it.handle(event) }
    }

    // or if you don't know the event type, this is also very unsafe
    fun notifyUnknown(loginEvent: LoginEvent) {
        listeners[loginEvent::class]?.asSequence()
            ?.filterIsInstance<LoginEventListener<LoginEvent>>()
            ?.forEach { it.handle(loginEvent) }
    }
}