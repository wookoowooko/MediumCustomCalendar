package io.wookoo.mediumcustomcalendar.presentation.calendar.uifuncs

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.wookoo.mediumcustomcalendar.R
import java.time.Month

@Composable
internal fun Month.getLocalizedMonthName(): String {
    val monthStringId = when (this) {
        Month.JANUARY -> R.string.january
        Month.FEBRUARY -> R.string.february
        Month.MARCH -> R.string.march
        Month.APRIL -> R.string.april
        Month.MAY -> R.string.may
        Month.JUNE -> R.string.june
        Month.JULY -> R.string.july
        Month.AUGUST -> R.string.august
        Month.SEPTEMBER -> R.string.september
        Month.OCTOBER -> R.string.october
        Month.NOVEMBER -> R.string.november
        Month.DECEMBER -> R.string.december
        else -> R.string.incorrect_month
    }
    return stringResource(monthStringId)
}
