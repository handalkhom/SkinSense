package com.capstone.skinsense.data

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_preferences")

class TokenPreferences(private val context: Context) {

    companion object {
        private val USER_ID_KEY = stringPreferencesKey("user_id")
        private val LOGIN_TOKEN_KEY = stringPreferencesKey("login_token")
        private val USERNAME_KEY = stringPreferencesKey("username")
        private val NAME_KEY = stringPreferencesKey("name")
        private val EMAIL_KEY = stringPreferencesKey("email")
        private val PHONE_KEY = stringPreferencesKey("phone")
    }

    // Save userId
    suspend fun saveUserId(userId: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = userId
        }
    }

    // Save Token
    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[LOGIN_TOKEN_KEY] = token
        }
    }

    // Save User Profile
    suspend fun saveUserProfile(username: String, name: String, email: String, phone: String) {
        context.dataStore.edit { preferences ->
            preferences[USERNAME_KEY] = username
            preferences[NAME_KEY] = name
            preferences[EMAIL_KEY] = email
            preferences[PHONE_KEY] = phone
        }
    }

    // Get userId
    val userId: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[USER_ID_KEY]
    }

    // Get Token
    val token: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[LOGIN_TOKEN_KEY]
    }

    // Get Username
    val username: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[USERNAME_KEY]
    }

    // Get Name
    val name: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[NAME_KEY]
    }

    // Get Email
    val email: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[EMAIL_KEY]
    }

    // Get Phone
    val phone: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[PHONE_KEY]
    }

    // Clear All Data
    suspend fun clearData() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    // Clear Token
    suspend fun clearToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(LOGIN_TOKEN_KEY)
        }
    }
}
