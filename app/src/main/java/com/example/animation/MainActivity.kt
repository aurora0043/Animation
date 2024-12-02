package com.example.animation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.animation.ui.theme.AnimationTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Animation(m = Modifier.padding(innerPadding))

                }
            }
        }
    }
}

@Composable

fun Animation(m: Modifier) {
    var appear by remember { mutableStateOf(true) } //背景出現
    var expanded by remember { mutableStateOf(true) } //背景延展
    var fly by remember { mutableStateOf(false) } //火箭升空

    Column {
        Button(
            onClick = { appear = !appear },
            modifier = m
        ) {
            if (appear) Text(text = "星空背景圖消失")
            else Text(text = "星空背景圖出現")
        }

        AnimatedVisibility(
            visible = appear,
            enter = fadeIn(
                initialAlpha = 0.1f,
                animationSpec = tween(durationMillis = 5000)),

            exit = fadeOut(
                animationSpec = tween(durationMillis = 5000))
        ) {

            Image(
                painter = painterResource(id = R.drawable.sky),
                contentDescription = "星空背景圖"
            )
        }
    }
}
