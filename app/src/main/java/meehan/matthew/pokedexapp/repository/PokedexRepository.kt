package meehan.matthew.pokedexapp.repository

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import meehan.matthew.pokedexapp.AllPokemonQuery
import meehan.matthew.pokedexapp.Database
import meehan.matthew.pokedexapp.mapToPokemonListResponse
import meehan.matthew.pokedexapp.models.PokemonItemData
import meehan.matthew.pokedexapp.network.ApiResponse
import meehan.matthew.pokedexapp.network.makeNetworkRequest
import meehan.matthew.pokedexapp.toPokemonListResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokedexRepository @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val apolloClient: ApolloClient,
    private val database: Database
) {

    suspend fun getAndPersistRemotePokemonList(limit: Int = DEFAULT_POKEMON_LIMIT): ApiResponse<AllPokemonQuery.Data> {
        val response = withContext(dispatcher) {
            makeNetworkRequest {
                apolloClient.query(
                    AllPokemonQuery(
                        limit = Optional.Present(limit)
                    )
                ).execute()
            }
        }
        return when (response) {
            is ApiResponse.Error -> response
            is ApiResponse.Success -> {
                persistAllPokemon(
                    pokemonList = response.data.allPokemon.toPokemonListResponse()
                )
                response
            }
        }
    }

    private fun persistAllPokemon(pokemonList: List<PokemonItemData>) =
        pokemonList.forEach {
            persistSinglePokemon(it)
        }

    private fun persistSinglePokemon(pokemon: PokemonItemData) =
        database.pokemonQueries.insertOrReplacePokemon(
            id = pokemon.id,
            name = pokemon.name,
            types = pokemon.types,
            sprite = pokemon.sprite
        )

    fun getLocalPokemonList() = database.pokemonQueries
        .selectAll()
        .asFlow()
        .mapToList()
        .mapToPokemonListResponse()

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

    fun clearLocalPokemonData() = database.pokemonQueries.deleteAllPokemon()

    companion object {
        private const val DEFAULT_POKEMON_LIMIT = 150
    }
}
