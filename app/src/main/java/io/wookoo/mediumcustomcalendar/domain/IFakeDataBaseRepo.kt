package io.wookoo.mediumcustomcalendar.domain

import io.wookoo.mediumcustomcalendar.presentation.calendar.mvi.DateToProgress
import kotlinx.coroutines.flow.Flow

interface IFakeDataBaseRepo {
    fun loadFakeCalendarData():  Flow<DateToProgress>
}