package com.grauen.composetest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.*
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ColumnScope.align
import androidx.compose.foundation.layout.ColumnScope.weight
import androidx.compose.foundation.layout.RowScope.align
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.imageFromResource
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.grauen.composetest.ui.ButtonsAnimation
import com.grauen.composetest.ui.ComposeTestTheme

class MainActivity : AppCompatActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background,modifier = Modifier.fillMaxSize()) {
                    Box(modifier = Modifier.align(Alignment.Bottom),gravity = Alignment.Center){
                        ButtonsAnimation()
                    }
                }
            }
        }
    }
}


@Composable
@Preview
fun screenPreview() {
    ComposeTestTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background,modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.fillMaxSize().align(Alignment.Bottom)){
                ButtonsAnimation()
            }
        }
    }
}
