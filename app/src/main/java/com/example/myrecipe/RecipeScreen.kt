package com.example.myrecipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun RecipeScreen(modifier: Modifier = Modifier,
                 viewModel: MainViewModel.RecipeState,
                 navigationToDetail: (Category) ->Unit) {
    // viewModel obj
    val mainViewModel : MainViewModel = viewModel()
    val viewState by mainViewModel.categoriesState

    // Box where first loading process second if error occurs and last main work
    Box(modifier = Modifier.fillMaxSize()) {
        when {
            viewState.loading ->{
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }
            viewState.error != null -> {
                Text(text = "Error occurred")
            }
            else -> {
                CategoryScreen(categories = viewState.list,navigationToDetail)
            }
        }
    }

}
// item list in Grid
@Composable
fun CategoryScreen(categories : List<Category>,
                   navigationToDetail: (Category) ->Unit) {
    LazyVerticalGrid(GridCells.Fixed(2),
                     modifier = Modifier.fillMaxSize()) {
        items(categories) {
            category -> 
            CategoryItem(category = category,navigationToDetail)
        }

    }
}

// how each item should look like
@Composable
fun CategoryItem( category: Category,
                  navigationToDetail: (Category) ->Unit) {
    Column(modifier = Modifier
        .padding(8.dp)
        .fillMaxSize()
        .clickable { navigationToDetail(category) },
        horizontalAlignment = Alignment.CenterHorizontally) {
        //image
        Image(painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f))

        //text
        Text(text = category.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )
    }

}