package io.wookoo.mediumcustomcalendar.presentation.calendar.uifuncs

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

internal fun generateDaysForMonth(month: YearMonth): List<LocalDate?> {
    val firstDayOfWeek = DayOfWeek.MONDAY
    val days = mutableListOf<LocalDate?>()

    val firstDayOfMonth = month.atDay(1)
    val emptyDays = (firstDayOfMonth.dayOfWeek.value - firstDayOfWeek.value + 7) % 7
    repeat(emptyDays) { days.add(null) }

    for (day in 1..month.lengthOfMonth()) {
        days.add(month.atDay(day))
    }

    while (days.size % 7 != 0) {
        days.add(null)
    }

    return days
}
