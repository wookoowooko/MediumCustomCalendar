package io.wookoo.mediumcustomcalendar.presentation.calendar.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.wookoo.mediumcustomcalendar.presentation.calendar.mvi.DateToProgress
import io.wookoo.mediumcustomcalendar.presentation.calendar.uifuncs.generateDaysForMonth
import io.wookoo.mediumcustomcalendar.presentation.calendar.uifuncs.getLocalizedDayOfWeekName
import io.wookoo.mediumcustomcalendar.presentation.calendar.uifuncs.getLocalizedMonthName
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth


@Composable
internal fun MonthView(
    month: YearMonth,
    progress: DateToProgress,
    onDateClick: (LocalDate, Double) -> Unit,
    onErrorClick: () -> Unit,
) {
    val monthString = month.month.getLocalizedMonthName()
    val yearString = month.year.toString()
    val days = generateDaysForMonth(month)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = "$monthString $yearString",
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.Bold,
            color = Color.LightGray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 16.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            DayOfWeek.entries.forEach { dayOfWeek ->
                val localizedDayName = getLocalizedDayOfWeekName(dayOfWeek)
                Text(
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    color = Color.LightGray,
                    text = localizedDayName.take(2), //optional
                    fontSize = MaterialTheme.typography.labelMedium.fontSize
                )
            }
        }
        LazyVerticalGrid(
            userScrollEnabled = false,
            columns = GridCells.Fixed(7),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(0.dp, 350.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(
                items = days,
                key = { index, _ -> index }
            ) { _, day ->
                DayView(
                    date = day,
                    progress = progress[day] ?: 0.0,
                    onDayClick = {
                        day?.let { localDate ->
                            onDateClick(localDate, progress[day] ?: 0.0)
                        }
                    },
                    onErrorClick = {
                        onErrorClick()
                    }
                )
            }
        }
    }
}

@Composable
@Preview
private fun MonthViewPreview() {
    MonthView(
        month = YearMonth.now(),
        progress = emptyMap(),
        onDateClick = { _, _ -> },
        onErrorClick = {}
    )
}