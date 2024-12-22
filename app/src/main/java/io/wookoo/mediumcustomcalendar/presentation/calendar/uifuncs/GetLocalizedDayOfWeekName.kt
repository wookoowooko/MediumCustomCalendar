package io.wookoo.mediumcustomcalendar.presentation.calendar.uifuncs

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.wookoo.mediumcustomcalendar.R
import java.time.DayOfWeek

@Composable
internal fun getLocalizedDayOfWeekName(dayOfWeek: DayOfWeek): String {
    val dayStringId = when (dayOfWeek) {
        DayOfWeek.MONDAY -> R.string.monday
        DayOfWeek.TUESDAY -> R.string.tuesday
        DayOfWeek.WEDNESDAY -> R.string.wednesday
        DayOfWeek.THURSDAY -> R.string.thursday
        DayOfWeek.FRIDAY ->R.string.friday
        DayOfWeek.SATURDAY -> R.string.saturday
        DayOfWeek.SUNDAY -> R.string.sunday
    }
    return stringResource(dayStringId)
}

