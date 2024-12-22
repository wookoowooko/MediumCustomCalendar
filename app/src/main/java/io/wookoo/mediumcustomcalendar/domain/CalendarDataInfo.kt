package io.wookoo.mediumcustomcalendar.domain

import java.time.YearMonth

data class CalendarDataInfo(
    val currentMonthIndex: Int = 0,
    val months: List<YearMonth> = emptyList()
)