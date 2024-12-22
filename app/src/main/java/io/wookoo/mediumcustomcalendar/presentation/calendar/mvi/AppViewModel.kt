package io.wookoo.mediumcustomcalendar.presentation.calendar.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.wookoo.mediumcustomcalendar.domain.CalendarPrepareUseCase
import io.wookoo.mediumcustomcalendar.domain.IFakeDataBaseRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppViewModel(
    fakeDataBaseRepo: IFakeDataBaseRepo,
    calendarPrepareUseCase: CalendarPrepareUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(CalendarState())
    val state = combine(
        _state,
        fakeDataBaseRepo.loadFakeCalendarData().onEach { dateToProgress ->
            _state.update { it.copy(dateToProgress = dateToProgress) }
        }
    ) { state, dateToProgress ->
        state.copy(dateToProgress = dateToProgress)
    }.stateIn(viewModelScope, SharingStarted.Lazily, CalendarState())

    init {
        viewModelScope.launch {
            _state.update { it.copy(calendarDataInfo = calendarPrepareUseCase()) }
        }
    }
}
