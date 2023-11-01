package pages

import components.BirdsGrid
import components.BirdsUiState
import components.BirdsViewModel
import components.CategoryButtons
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun BirdsPage(viewModel: BirdsViewModel, uiState: BirdsUiState) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CategoryButtons(uiState.categories, viewModel)
        BirdsGrid(uiState.selectedImages)
    }
}