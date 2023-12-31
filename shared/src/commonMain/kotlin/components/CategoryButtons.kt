package components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CategoryButtons(categories: Set<String>, viewModel: BirdsViewModel) {
    Row (
        modifier = Modifier.fillMaxWidth().padding(5.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ){
        //CategoryButtons(uiState.categories, viewModel)
        for(category in categories) {
            Button(onClick = {
                viewModel.selectCategory(category)
            }, modifier = Modifier.aspectRatio(1.0f).fillMaxSize().weight(1.0f),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    focusedElevation = 0.dp
                )) {
                Text(category)
            }
        }
    }
}