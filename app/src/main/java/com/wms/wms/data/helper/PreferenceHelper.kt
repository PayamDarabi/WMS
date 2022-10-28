package com.wms.wms.data.helper

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

object PreferenceHelper {
    var applicationContext: Context? = null

    fun initilize(context: Context) {
        applicationContext = context;
    }

    fun defaultPrefs(context: Context): SharedPreferences =
        context.getSharedPreferences("data", Context.MODE_PRIVATE)

    inline fun <reified T : Any> getObject(key: String, defaultValue: T? = null): T? {
        val result = applicationContext?.let { defaultPrefs(it).getString(key, null) }

        if (result === null) {
            return defaultValue
        }
        var gson = Gson()
        return gson.fromJson(result, T::class.java)
    }

    fun setObject(key: String, value: Any?) {
        val editor = applicationContext?.let { defaultPrefs(it).edit() }
        var gson = Gson()
        if (editor != null) {
            editor.putString(key, gson.toJson(value))
            editor.apply()
        }
    }

    fun getString(key: String, defaultValue: String? = null): String? {
        return applicationContext?.let { defaultPrefs(it).getString(key, defaultValue) }
    }

    fun setString(key: String, value: String?) {
        val editor = applicationContext?.let { defaultPrefs(it).edit() }
        if (editor != null) {
            editor.putString(key, value)
            editor.apply()
        }
    }
}