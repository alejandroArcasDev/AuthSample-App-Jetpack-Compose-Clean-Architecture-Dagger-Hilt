package alejandroarcas.authsample.data.local

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {

    suspend fun saveToken(token: String?)

    fun readToken(): Flow<String?>

    suspend fun clearToken()
}