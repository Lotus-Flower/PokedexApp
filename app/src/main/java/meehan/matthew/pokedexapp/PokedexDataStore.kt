package meehan.matthew.pokedexapp

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "pokedexDatastore")
private const val FAVORITES_KEY = "pokedexFavorites"

class PokedexDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getFavorites() = context.dataStore.data.map {
        it[stringSetPreferencesKey(FAVORITES_KEY)] ?: emptySet()
    }

    suspend fun addFavorite(id: String) = context.dataStore.edit {
        val currentFavorites = (it[stringSetPreferencesKey(FAVORITES_KEY)] ?: emptySet())
            .toMutableSet()
        currentFavorites.add(id)
        it[stringSetPreferencesKey(FAVORITES_KEY)] = currentFavorites
    }

    suspend fun removeFavorite(id: String) = context.dataStore.edit {
        val currentFavorites = (it[stringSetPreferencesKey(FAVORITES_KEY)] ?: emptySet())
            .toMutableSet()
        currentFavorites.remove(id)
        it[stringSetPreferencesKey(FAVORITES_KEY)] = currentFavorites
    }
}
