package io.wookoo.mediumcustomcalendar.presentation.calendar.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.LocalDate

@Composable
internal fun DayView(
    date: LocalDate?,
    progress: Double,
    onDayClick: () -> Unit,
    onErrorClick: () -> Unit,
) {
    val isToday = date == LocalDate.now()

    Box(
        modifier = Modifier
            .size(48.dp)
            .padding(4.dp)
            .clickable {
                if (progress != 0.0) {
                    onDayClick()
                } else {
                    onErrorClick()
                }
            },
        contentAlignment = Alignment.Center
    ) {
        if (date != null) {
            CanvasProgress(progress, isToday)

            Text(
                color = Color.White,
                text = date.dayOfMonth.toString(),
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
@Preview
private fun DayViewPreview() {
    DayView(
        date = LocalDate.now(),
        progress = 55.0,
        onDayClick = {},
        onErrorClick = {}
    )
}