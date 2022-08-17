package meehan.matthew.pokedexapp

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import meehan.matthew.pokedexapp.models.PokemonItemData
import meehanmatthewpokedexapp.Pokemon

fun List<AllPokemonQuery.AllPokemon?>?.toPokemonListResponse() =
    this?.mapNotNull { query ->
        PokemonItemData(
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

fun  Flow<List<Pokemon>>.mapToPokemonListResponse() =
    this.map { list ->
        list.map { pokemon ->
            PokemonItemData(
                id = pokemon.id,
                name = pokemon.name,
                types = pokemon.types,
                sprite = pokemon.sprite
            )
        }
    }
