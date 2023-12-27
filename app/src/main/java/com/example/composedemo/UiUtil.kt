package com.example.composedemo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

object UiUtil {

    @Composable
    fun ProduceButton(modifier: Modifier = Modifier, description: String, onClick: () -> Unit) {
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
            .clickable { onClick() }
            .clip(CircleShape)
            .then(modifier)
        ) {
            Text(text = description, fontSize = 36.sp, color = Color.Black)
        }
    }
}