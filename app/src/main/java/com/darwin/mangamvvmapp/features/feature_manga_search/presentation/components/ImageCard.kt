package com.darwin.mangamvvmapp.features.feature_manga_search.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.darwin.mangamvvmapp.R

@Composable
fun ImageCard(
    modifier: Modifier = Modifier,
    url:String,
    contentDescription:String,
    title:String
){
    Card(
        modifier = Modifier.fillMaxWidth().height(280.dp).padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.elevatedCardElevation(5.dp)
    ) {
        Box(modifier = modifier.fillMaxWidth()){
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = url,
                contentDescription = contentDescription,
                contentScale = ContentScale.FillBounds,
                placeholder = painterResource(R.drawable.placeholder_reader),
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 300f
                        )
                    )
            )
            Text(
                text = title,
                style = TextStyle(color = MaterialTheme.colorScheme.onBackground, fontSize = 20.sp),
                modifier = Modifier.padding(12.dp).align(Alignment.BottomCenter)
            )
        }

    }

}