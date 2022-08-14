package meehan.matthew.pokedexapp

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import meehan.matthew.pokedexapp.ui.theme.PokedexAppTheme
import meehan.matthew.pokedexapp.ui.theme.Purple200

@Composable
fun PokedexItemView(
    item: PokedexItemState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        AsyncImage(
            model = item.data.sprite,
            placeholder = painterResource(
                id = R.drawable.ic_launcher_foreground
            ),
            contentDescription = item.data.name,
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
                text = item.data.name
            )
            Text(
                text = item.data.types
            )
        }
        Spacer(
            modifier = Modifier.weight(
                weight = 1f
            )
        )
        IconToggleButton(
            checked = false,
            onCheckedChange = {},
            modifier = Modifier
                .align(
                    alignment = Alignment.CenterVertically
                )
        ) {
            Icon(
                imageVector = Icons.TwoTone.Star,
                contentDescription = "",
                tint = Color.LightGray
            )
        }
    }
}

@Preview
@Composable
fun PokedexItemViewPreview() {
    PokedexAppTheme {
        PokedexItemView(
            PokedexItemState(
               data = PokemonListResponse(
                   name = "Bulbasaur",
                   types = "Grass, Poison",
                   sprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
               ),
               favorite = false
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        )
    }
}

@Preview
@Composable
fun PokedexItemViewPreviewFavorite() {
    PokedexAppTheme {
        PokedexItemView(
            PokedexItemState(
                data = PokemonListResponse(
                    name = "Bulbasaur",
                    types = "Grass, Poison",
                    sprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
                ),
                favorite = true
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        )
    }
}
