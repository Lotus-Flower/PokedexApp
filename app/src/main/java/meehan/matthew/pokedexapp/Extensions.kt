package meehan.matthew.pokedexapp

fun List<AllPokemonQuery.AllPokemon?>?.toPokemonListResponse() =
    this?.mapNotNull { query ->
        PokemonListResponse(
            name = query?.name.orEmpty().replaceFirstChar { it.uppercase() },
            types = query?.types?.joinToString(
                separator = ", "
            ) { it?.name.orEmpty() }.orEmpty(),
            sprite = query?.sprites?.front_default.orEmpty()
        )
    }.orEmpty()
