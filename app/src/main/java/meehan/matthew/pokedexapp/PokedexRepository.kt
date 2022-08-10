package meehan.matthew.pokedexapp

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokedexRepository @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val apolloClient: ApolloClient
) {
    suspend fun getPokemonList() = withContext(dispatcher) {
        apolloClient.query(
            AllPokemonQuery(
                limit = Optional.Present(150)
            )
        ).execute().data?.allPokemon?.mapNotNull { query ->
            PokemonListResponse(
                name = query?.name.orEmpty().replaceFirstChar { it.uppercase() },
                types = query?.types?.joinToString(
                    separator = ", "
                ) { it?.name.orEmpty() }.orEmpty(),
                sprite = query?.sprites?.front_default.orEmpty()
            )
        }.orEmpty()
    }
}
