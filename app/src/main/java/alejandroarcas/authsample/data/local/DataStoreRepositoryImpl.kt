package alejandroarcas.authsample.data.local

import alejandroarcas.authsample.util.Constants.PREFERENCES_NAME
import alejandroarcas.authsample.util.Constants.PREFERENCES_TOKEN
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)


class DataStoreRepositoryImpl @Inject constructor(
    @ApplicationContext context: Context
): DataStoreRepository {

    private object PreferencesKey {
        val token = stringPreferencesKey(name = PREFERENCES_TOKEN)
    }

    private val dataStore = context.dataStore

    override suspend fun saveToken(token: String?) {
        token?.let {
            dataStore.edit { preferences ->
                preferences[PreferencesKey.token] = token
            }
        }
    }

    override fun readToken(): Flow<String?> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[PreferencesKey.token]
            }
    }

    override suspend fun clearToken() {
        dataStore.edit { preferences ->
            preferences.remove(PreferencesKey.token)
        }
    }

}