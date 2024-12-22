package io.wookoo.mediumcustomcalendar.presentation.calendar.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.wookoo.mediumcustomcalendar.domain.CalendarDataInfo
import io.wookoo.mediumcustomcalendar.presentation.calendar.mvi.DateToProgress
import java.time.LocalDate


@Composable
internal fun CustomCalendar(
    monthProgress: DateToProgress,
    calendarDataInfo: CalendarDataInfo,
    modifier: Modifier = Modifier,
    onDateClick: (LocalDate, Double) -> Unit,
    onErrorClick: () -> Unit,
) {
    // need loader
    if (calendarDataInfo.months.isEmpty()) {
        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
        return
    }
    val scrollState = rememberLazyListState(
        initialFirstVisibleItemIndex = calendarDataInfo.currentMonthIndex
    )

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        state = scrollState,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(calendarDataInfo.months) { month ->
            MonthView(
                month = month,
                progress = monthProgress.filterKeys { it.year == month.year && it.monthValue == month.monthValue },
                onDateClick = onDateClick,
                onErrorClick = {
                    onErrorClick()
                }
            )
        }
    }
}
