package com.darwin.mangamvvmapp.service

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    fun saveSelectedOption(option: String) {
        sharedPreferences.edit().putString("selected_option", option).apply()
    }

    fun getSelectedOption(): String? {
        return sharedPreferences.getString("selected_option", null)
    }
}
