package components

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import model.BirdImage

data class BirdsUiState(
    val images: List<BirdImage> = emptyList(),
    val selectedCategory: String? = null
) {
    val categories = images.map { it.category }.toSet()
    val selectedImages = images.filter { it.category == selectedCategory }
}
class BirdsViewModel: ViewModel() {
    private val _uiState = MutableStateFlow<BirdsUiState>(BirdsUiState())

    // Only members of the BirdViewModel can update the state
    // But external consumers can read the UI state
    val uiState = _uiState.asStateFlow()

    val httpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    init {
        updateImages()
    }

    fun selectCategory(category: String) {
        _uiState.update {
            it.copy(selectedCategory = category)
        }
    }

    override fun onCleared() {
        httpClient.close()
    }

    fun updateImages() {
        viewModelScope.launch {
            val images = getImages()
            _uiState.update { it.copy(images = images) }
        }
    }

    suspend fun getImages(): List<BirdImage> {
        val images = httpClient
            .get("https://sebi.io/demo-image-api/pictures.json")
            .body<List<BirdImage>>()

        return images
    }
}