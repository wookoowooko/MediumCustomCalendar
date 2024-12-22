package io.wookoo.mediumcustomcalendar.presentation.calendar.mvi

import io.wookoo.mediumcustomcalendar.domain.CalendarDataInfo
import java.time.LocalDate

typealias DateToProgress = Map<LocalDate, Double>

data class CalendarState(
    val dateToProgress: DateToProgress = emptyMap(),
    val calendarDataInfo: CalendarDataInfo = CalendarDataInfo(),
)
