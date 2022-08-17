package meehan.matthew.pokedexapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import meehan.matthew.pokedexapp.ui.theme.PokedexAppTheme

@Composable
fun PokedexList(
    data: List<PokedexItemState>,
    modifier: Modifier = Modifier
) {
    when (data.isNotEmpty()) {
        true -> LazyColumn(
            modifier = modifier
        ) {
            items(
                items = data,
                itemContent = { item ->
                    PokedexItemView(
                        item = item
                    )
                }
            )
        }
        false -> EmptyDataView(
            modifier = modifier
        )
    }
}

@Composable
fun EmptyDataView(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            text = stringResource(
                id = R.string.no_data
            )
        )
    }
}

@Preview
@Composable
fun PokedexListPreview(
    @PreviewParameter(
        PokedexItemStateListPreviewProvider::class
    ) data: List<PokedexItemState>
) {
    PokedexAppTheme {
        PokedexList(
            data = data,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}
