package meehan.matthew.pokedexapp

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.twotone.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import coil.compose.AsyncImage
import meehan.matthew.pokedexapp.ui.theme.PokedexAppTheme
import meehan.matthew.pokedexapp.ui.theme.Purple200
import meehan.matthew.pokedexapp.ui.theme.dimens
import meehan.matthew.pokedexapp.ui.theme.spriteImageSize

@Composable
fun PokedexItemView(
    item: PokedexItemState,
    onItemChecked: (Boolean, PokemonItemResponse) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        PokedexItemImageView(
            imageUrl = item.data.sprite,
            contentDescription = item.data.name,
            modifier = Modifier
                .size(
                    size = spriteImageSize
                )
        )
        PokedexItemTextView(
            nameText = item.data.name,
            typeText = item.data.types,
            modifier = Modifier
                .padding(
                    horizontal = dimens.spacingHalf,
                    vertical = dimens.spacingStandard
                )
        )
        Spacer(
            modifier = Modifier.weight(
                weight = 1f
            )
        )
        PokedexItemFavoriteButtonView(
            favorite = item.favorite,
            onCheckedChange = {
                onItemChecked.invoke(it, item.data)
            },
            modifier = Modifier
                .align(
                    alignment = Alignment.CenterVertically
                )
        )
    }
}

@Composable
fun PokedexItemImageView(
    imageUrl: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    AsyncImage(
        model = imageUrl,
        placeholder = painterResource(
            id = R.drawable.ic_launcher_foreground
        ),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}

@Composable
fun PokedexItemTextView(
    nameText: String,
    typeText: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = nameText
        )
        Text(
            text = typeText
        )
    }
}

@Composable
fun PokedexItemFavoriteButtonView(
    favorite: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    IconToggleButton(
        checked = favorite,
        onCheckedChange = {
            onCheckedChange.invoke(
                it
            )
        },
        modifier = modifier
    ) {
        Icon(
            imageVector = when (favorite) {
                true -> Icons.Filled.Star
                false -> Icons.TwoTone.Star
            },
            contentDescription = stringResource(
                id = R.string.favorite
            ),
            tint = when (favorite) {
                true -> Purple200
                false -> Color.LightGray
            }
        )
    }
}

@Preview
@Composable
fun PokedexItemImagePreview() {
    PokedexAppTheme {
        PokedexItemImageView(
            imageUrl = ""
        )
    }
}

@Preview
@Composable
fun PokedexItemTextPreview() {
    PokedexAppTheme {
        PokedexItemTextView(
            nameText = "Squirtle",
            typeText = "Water"
        )
    }
}

@Preview
@Composable
fun PokedexItemFavoriteButtonPreview() {
    PokedexAppTheme {
        PokedexItemFavoriteButtonView(
            favorite = false,
            onCheckedChange = {}
        )
    }
}

@Preview
@Composable
fun PokedexItemPreview(
    @PreviewParameter(
        PokedexItemStatePreviewProvider::class
    ) state: PokedexItemState
) {
    PokedexAppTheme {
        PokedexItemView(
            item = state,
            onItemChecked = { _, _ -> },
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        )
    }
}
