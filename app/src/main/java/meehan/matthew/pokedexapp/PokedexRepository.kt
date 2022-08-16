package meehan.matthew.pokedexapp

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokedexRepository @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val apolloClient: ApolloClient,
    private val dataStore: PokedexDataStore,
    private val database: Database
) {
    init {
        database.pokemonQueries.deleteAllPokemon()
    }

    suspend fun getRemotePokemonList(limit: Int = DEFAULT_POKEMON_LIMIT) = withContext(dispatcher) {
        apolloClient.query(
            AllPokemonQuery(
                limit = Optional.Present(limit)
            )
        ).execute().data?.allPokemon.toPokemonListResponse()
    }

    suspend fun getSingleRemotePokemonById(id: Int) = withContext(dispatcher) {
        apolloClient.query(
            SinglePokemonQuery(
                pokemonId = id
            )
        ).execute().data?.pokemon.toPokemonItemResponse()
    }

    fun getFavorites() = database.pokemonQueries
        .selectAllFavorites()
        .asFlow()
        .mapToList()
        .mapToPokemonListResponse()

    fun addFavorite(id: String) = database.pokemonQueries.addFavoritePokemonById(
        id = id
    )

    fun removeFavorite(id: String) = database.pokemonQueries.removeFavoritePokemonById(
        id = id
    )

    fun persistPokemon(pokemon: PokemonItemResponse) {
        database.pokemonQueries.insertOrReplacePokemon(
            id = pokemon.id,
            name = pokemon.name,
            types = pokemon.types,
            sprite = pokemon.sprite
        )
    }

    fun getLocalPokemonList() = database.pokemonQueries
        .selectAll()
        .asFlow()
        .mapToList()
        .mapToPokemonListResponse()

    companion object {
        private const val DEFAULT_POKEMON_LIMIT = 150
    }
}
