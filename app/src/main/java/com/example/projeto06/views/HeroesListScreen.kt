package com.example.projeto06.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.projeto06.R
import com.example.projeto06.data.Hero

private const val BASE_URL = "https://api.opendota.com"  // val url

@Composable
fun HeroListScreen(
    heroesViewModel: HeroesViewModel          //recebe como parametro
) {
    val heroesList by heroesViewModel.heroList.observeAsState(listOf())
    HeroList(heroList = heroesList)
}

@OptIn(ExperimentalFoundationApi::class) //biblioteca experimental
@Composable
fun HeroList(
    heroList: List<Hero>
) {
    LazyVerticalGrid(
        modifier = Modifier.background(Color.Yellow),
        cells = GridCells.Fixed(2)
    ){
        items(heroList){ hero ->
            HeroEntry(hero = hero)
        }
    }
}


@Composable
fun HeroEntry(
    hero: Hero
){
    val density = LocalDensity.current.density
    val width = remember { mutableStateOf(0F)}
    val height = remember {mutableStateOf(0F)}
    Card(
        modifier = Modifier.padding(6.dp),
        elevation = 8.dp
    ) {
        Box(){
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(BASE_URL+hero.img)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.pokemon),
                contentDescription = hero.localized_name,  // nome do personagem
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RectangleShape)
                    .onGloballyPositioned {
                        width.value = it.size.width/density
                        height.value = it.size.height/density
                    }
            )
            Box(modifier = Modifier
                .size(width.value.dp, height.value.dp)
                .background(
                    Brush.verticalGradient(
                        listOf(Color.Transparent, Color.Black),
                        100F,
                        500F,
                    )
                )
            )
            Text(
                text = hero.localized_name,  //nome do personagem
                modifier = Modifier
                    .align(Alignment.BottomCenter),
                style = MaterialTheme.typography.h5.copy(
                    color = Color.White, fontWeight = FontWeight.Bold
                ),
            )
        }
    }
}



