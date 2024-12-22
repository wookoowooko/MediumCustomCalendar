package io.wookoo.mediumcustomcalendar.presentation.calendar.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
internal fun CanvasProgress(progress: Double, isToday: Boolean) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val strokeWidth = 3.dp.toPx()
        val size = size.minDimension

        drawArc(
            color = Color.Yellow,
            startAngle = -90f,
            sweepAngle = (360f * progress / 100).toFloat(),
            useCenter = false,
            style = Stroke(width = strokeWidth),
            size = Size(size, size),
            topLeft = Offset(
                (this.size.width - size) / 2,
                (this.size.height - size) / 2
            )
        )

        if (isToday) {
            drawCircle(
                color = Magenta,
                radius = size / 2,
                style = Fill
            )
        }
    }
}

@Composable
@Preview
private fun CanvasProgressPreview() {
    CanvasProgress(progress = 55.0, isToday = true)
}
