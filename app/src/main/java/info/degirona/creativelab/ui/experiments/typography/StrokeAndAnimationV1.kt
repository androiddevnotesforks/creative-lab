package info.degirona.creativelab.ui.experiments.typography

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import info.degirona.creativelab.ui.experiments.shaders.BandShader
import info.degirona.creativelab.ui.theme.fontFamily
import info.degirona.creativelab.ui.utils.FrameEffect

@OptIn(ExperimentalTextApi::class)
@Composable
fun StrokeAndAnimationV1(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        val bandShader = remember { BandShader() }
        val shaderBrush = remember { ShaderBrush(bandShader) }
        var time by remember { mutableStateOf(0f) }
        FrameEffect(Unit) {
            time = it
        }
        Text(
            text = "Hello from Creative Lab!",
            color = MaterialTheme.colorScheme.onSurface,
            style = TextStyle.Default.copy(
                textAlign = TextAlign.Center,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily,
                drawStyle = Stroke(
                    miter = 10f,
                    width = 5f,
                    join = StrokeJoin.Round
                )
            )
        )
        Text(
            text = "Hello from Creative Lab!",
            color = MaterialTheme.colorScheme.onSurface,
            style = TextStyle.Default.copy(
                textAlign = TextAlign.Center,
                fontSize = 40.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                brush = shaderBrush.also {
                    bandShader.updateTime(time = time)
                }
            ),
            modifier = Modifier
                .onSizeChanged {
                    bandShader.updateResolution(it.toSize())
                }
        )
    }
}