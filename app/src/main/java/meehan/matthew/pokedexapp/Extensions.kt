package meehan.matthew.pokedexapp

fun List<AllPokemonQuery.AllPokemon?>?.toPokemonListResponse() =
    this?.mapNotNull { query ->
        PokemonListResponse(
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
