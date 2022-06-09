package com.ayamgorengenak.capfits.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayamgorengenak.capfits.UserPreference
import kotlinx.coroutines.launch

class LoginViewModel(private val pref: UserPreference) : ViewModel() {
    fun login() {
        viewModelScope.launch {
            pref.login()
        }
    }

    fun saveUser(user: String) {
        viewModelScope.launch {
            pref.saveUser(user)
        }
    }
}