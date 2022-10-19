package com.wms.wms.data.helper

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson

object PreferenceHelper {

    fun defaultPrefs(context: Context): SharedPreferences =
        context.getSharedPreferences("data",Context.MODE_PRIVATE)

    inline fun <reified T : Any> getObject(context: Context, key: String, defaultValue: T? = null): T? {
        val result = defaultPrefs(context).getString(key, null)

        if (result === null) {
            return defaultValue
        }
        var gson = Gson()
        return gson.fromJson(result, T::class.java)
    }

    fun setObject(context: Context, key: String, value: Any?) {
        val editor = defaultPrefs(context).edit()
        var gson = Gson()
        editor.putString(key, gson.toJson(value))
        editor.apply()
    }

    fun getString(context: Context, key: String, defaultValue: String? = null): String? {
        return defaultPrefs(context).getString(key, defaultValue)
    }

    fun setString(context: Context, key: String, value: String?) {
        val editor = defaultPrefs(context).edit()
        editor.putString(key, value)
        editor.apply()
    }
}