package meehan.matthew.pokedexapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel
import meehan.matthew.pokedexapp.ui.theme.PokedexAppTheme

@Composable
fun PokedexAppScreen(
    viewModel: PokedexAppViewModel
) {
    val state by viewModel.collectAsState()

    PokedexAppTheme {
        PokedexApp(
            state = state,
            onNavigationButtonSelected = { navHostController, navigationRoute ->
                viewModel.navigateToTopLevelDestination(
                    navController = navHostController,
                    navigationRoute = navigationRoute
                )
            }
        )
    }
}

@Composable
fun PokedexApp(
    state: PokedexAppViewModelState,
    onNavigationButtonSelected: (NavHostController, NavigationRoute) -> Unit,
    modifier: Modifier = Modifier
) {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomAppBar {
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.List,
                            contentDescription = stringResource(
                                id = R.string.pokedex_list
                            )
                        )
                    },
                    selected = state.selectedNavigationButton == NavigationRoute.POKEDEX_LIST_ROUTE,
                    onClick = {
                        onNavigationButtonSelected.invoke(
                            navController,
                            NavigationRoute.POKEDEX_LIST_ROUTE
                        )
                    }
                )
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = stringResource(
                                id = R.string.pokedex_favorites
                            )
                        )
                    },
                    selected = state.selectedNavigationButton == NavigationRoute.POKEDEX_FAVORITES_ROUTE,
                    onClick = {
                        onNavigationButtonSelected.invoke(
                            navController,
                            NavigationRoute.POKEDEX_FAVORITES_ROUTE
                        )
                    }
                )
            }
        },
        modifier = modifier
    ) { innerPadding ->
        when {
            state.isLoading -> LoadingView(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
            state.errorMessage.isNotEmpty() -> ErrorView(
                message = state.errorMessage,
                modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
            else -> Navigator(
                navHostController = navController,
                modifier = modifier
                    .padding(
                        paddingValues = innerPadding
                    )
            )
        }
    }
}

@Composable
fun Navigator(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = NavigationRoute.POKEDEX_LIST_ROUTE.route,
        modifier = modifier
    ) {
        composable(
            route = NavigationRoute.POKEDEX_LIST_ROUTE.route
        ) {
            PokedexListScreen(
                viewModel = mavericksViewModel()
            )
        }
        composable(
            route = NavigationRoute.POKEDEX_FAVORITES_ROUTE.route
        ) {
            PokedexFavoritesScreen(
                viewModel = mavericksViewModel()
            )
        }
    }
}

@Composable
fun ErrorView(
    message: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            text = message,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun LoadingView(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
       CircularProgressIndicator()
    }
}

@Preview
@Composable
fun ErrorPreview() {
    ErrorView(
        message = "Error",
        modifier = Modifier
            .fillMaxSize()
    )
}

@Preview
@Composable
fun LoadingPreview(
    modifier: Modifier = Modifier
) {
    LoadingView(
        modifier = Modifier
            .fillMaxSize()
    )
}

@Preview
@Composable
fun PokedexAppPreview() {
    PokedexAppTheme {
        PokedexApp(
            state = PokedexAppViewModelState(),
            onNavigationButtonSelected = {
                    _, _ ->
            }
        )
    }
}
