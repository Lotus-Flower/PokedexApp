package meehan.matthew.pokedexapp

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import meehan.matthew.pokedexapp.ui.theme.PokedexAppTheme

@Composable
fun PokedexItemView(
    item: PokemonListResponse,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        AsyncImage(
            model = item.sprite,
            placeholder = painterResource(
                id = R.drawable.ic_launcher_foreground
            ),
            contentDescription = item.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(
                    size = 80.dp
                )
        )
        Column(
            modifier = Modifier
                .padding(
                    horizontal = 4.dp,
                    vertical = 8.dp
                )
        ) {
            Text(
                text = item.name
            )
            Text(
                text = item.types
            )
        }
    }
}

@Preview
@Composable
fun PokedexItemViewPreview() {
    PokedexAppTheme {
        PokedexItemView(
            PokemonListResponse(
                name = "Bulbasaur",
                types = "Grass, Poison",
                sprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        )
    }
}
