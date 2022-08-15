package meehan.matthew.pokedexapp

fun List<AllPokemonQuery.AllPokemon?>?.toPokemonListResponse() =
    this?.mapNotNull { query ->
        PokemonItemResponse(
            id = query?.id?.toString() ?: return@mapNotNull null,
            name = query.name?.replaceFirstChar { it.uppercase() } ?: return@mapNotNull null,
            types = query.types?.mapNotNull { pokemonType ->
                pokemonType?.name
            }?.joinToString(
                separator = ", "
            ) { name ->
                name
            } ?: return@mapNotNull null,
            sprite = query.sprites?.front_default ?: return@mapNotNull null
        )
    }.orEmpty()

fun SinglePokemonQuery.Pokemon?.toPokemonItemResponse(): PokemonItemResponse? {
    this?.let { query ->
        return PokemonItemResponse(
            id = query.id?.toString() ?: return null,
            name = query.name?.replaceFirstChar { it.uppercase() } ?: return null,
            types = query.types?.mapNotNull { pokemonType ->
                pokemonType?.name
            }?.joinToString(
                separator = ", "
            ) { name ->
                name
            } ?: return null,
            sprite = query.sprites?.front_default ?: return null
        )
    } ?: run {
        return null
    }
}
