package com.chewie.mepet.utils

import android.content.Context

class SharedPreference(private var context: Context) {

    fun setId(id: Int) {
        val sharedPreferences = context.getSharedPreferences(ID_PROFILE, 0)
        val edit = sharedPreferences.edit()
        edit.putInt(PROFILE_ID_KEY, id)
        edit.apply()
    }

    fun getId(): Int {
        val sharedPreferences = context.getSharedPreferences(ID_PROFILE, 0)
        return sharedPreferences.getInt(PROFILE_ID_KEY, 0)
    }

    fun setJamPagi(jam: String?) {
        val sharedPreferences = context.getSharedPreferences(JAM_PROFILE, 0)
        val editor = sharedPreferences.edit()
        editor.putString(JAM_PAGI_KEY, jam)
        editor.apply()
    }

    fun getJamPagi(): String {
        val sharedPreferences = context.getSharedPreferences(JAM_PROFILE, 0)
        return sharedPreferences.getString(JAM_PAGI_KEY, "") ?: ""
    }

    fun setJamSiang(jam: String?) {
        val sharedPreferences = context.getSharedPreferences(JAM_PROFILE, 0)
        val editor = sharedPreferences.edit()
        editor.putString(JAM_SIANG_KEY, jam)
        editor.apply()
    }

    fun getJamSiang(): String {
        val sharedPreferences = context.getSharedPreferences(JAM_PROFILE, 0)
        return sharedPreferences.getString(JAM_SIANG_KEY, "") ?: ""
    }

    fun setJamMalam(jam: String?) {
        val sharedPreferences = context.getSharedPreferences(JAM_PROFILE, 0)
        val editor = sharedPreferences.edit()
        editor.putString(JAM_MALAM_KEY, jam)
        editor.apply()
    }

    fun getJamMalam(): String {
        val sharedPreferences = context.getSharedPreferences(JAM_PROFILE, 0)
        return sharedPreferences.getString(JAM_MALAM_KEY, "") ?: ""
    }
}