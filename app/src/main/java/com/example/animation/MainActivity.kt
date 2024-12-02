package com.example.animation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

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
    val buttonAngle by animateFloatAsState(
        if (appear) 360f else 0f,
        animationSpec = tween(durationMillis = 2500)
    )


    Column {
        Button(
            onClick = { appear = !appear },
            modifier = Modifier.rotate(buttonAngle)
            modifier = m
        ) {
            if (appear) Text(text = "星空背景圖消失")
            else Text(text = "星空背景圖出現")
        }

        AnimatedVisibility(
            visible = appear,
            enter = fadeIn(
                initialAlpha = 0.1f,
                animationSpec = tween(durationMillis = 5000))
                    + slideInHorizontally(
                animationSpec = tween(durationMillis = 5000)) { fullWidth ->
                fullWidth / 3
            },
            exit = fadeOut(
                animationSpec = tween(durationMillis = 5000))
                    + slideOutHorizontally(
                animationSpec = tween(durationMillis = 5000)) { fullWidth ->
                fullWidth / 3
            }
        ) {

            Image(
                painter = painterResource(id = R.drawable.sky),
                contentDescription = "星空背景圖",
                modifier = Modifier
                    .animateContentSize()
                    .fillMaxWidth()
                    .height(if (expanded) 600.dp else 400.dp)
                    .clickable() {
                        expanded = !expanded
                    }
            )
        }
    }
}

