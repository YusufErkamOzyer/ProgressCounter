package com.yusuferkamozyer.successcomposecomponent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            InputProgressNum()
        }
    }
}


@Composable
fun StateComponent(totalProgress: Int, currentProgress: Int) {
    val peaceSize = 360f / totalProgress
    var startAngle = 270f
    var space = 0.10f * peaceSize
    var sweepAngle = peaceSize * 0.90f
    Canvas(
        modifier = Modifier
            .size(500.dp)
            .padding(60.dp)
    ) { for (i in 0 until currentProgress) {
            drawArc(
                color = Color.Cyan,
                style = Stroke(width = 20f),
                startAngle = (startAngle + (space) * (i + 1) + sweepAngle * i) % 360f,
                sweepAngle = sweepAngle,
                useCenter = false,
                size = Size(500f, 500f),
                topLeft = Offset(0f, 0f)
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputProgressNum() {
    val inputValue = remember { mutableStateOf("") }
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        TextField(
            value = inputValue.value,
            onValueChange = { inputValue.value = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        println(inputValue.value)
        if (inputValue.value == "") {
            StateComponent(8, 0)
        } else {
            StateComponent(8, inputValue.value.toInt())
        }

    }
}

