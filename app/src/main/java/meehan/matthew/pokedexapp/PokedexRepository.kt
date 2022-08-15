package meehan.matthew.pokedexapp

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokedexRepository @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val apolloClient: ApolloClient,
    private val dataStore: PokedexDataStore
) {
    suspend fun getPokemonList(limit: Int = DEFAULT_POKEMON_LIMIT) = withContext(dispatcher) {
        apolloClient.query(
            AllPokemonQuery(
                limit = Optional.Present(limit)
            )
        ).execute().data?.allPokemon.toPokemonListResponse()
    }

    suspend fun getFavorites() = withContext(dispatcher) {
        dataStore.getFavorites()
    }

    suspend fun addFavorite(id: String) = withContext(dispatcher) {
        dataStore.addFavorite(
            id = id
        )
    }

    suspend fun removeFavorite(id: String) = withContext(dispatcher) {
        dataStore.removeFavorite(
            id = id
        )
    }

    companion object {
        private const val DEFAULT_POKEMON_LIMIT = 150
    }
}
