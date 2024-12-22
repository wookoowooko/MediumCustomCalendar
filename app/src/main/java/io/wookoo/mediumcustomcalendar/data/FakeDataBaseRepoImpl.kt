package io.wookoo.mediumcustomcalendar.data

import io.wookoo.mediumcustomcalendar.domain.IFakeDataBaseRepo
import io.wookoo.mediumcustomcalendar.presentation.calendar.mvi.DateToProgress
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.time.LocalDate

class FakeDataBaseRepoImpl(
    private val ioDispatcher: CoroutineDispatcher
) : IFakeDataBaseRepo {
    override fun loadFakeCalendarData(): Flow<DateToProgress> = flow {
        val data: DateToProgress = mapOf(
            LocalDate.of(2022, 1, 1) to 50.0,
            LocalDate.of(2023, 2, 2) to 75.0,
            LocalDate.of(2024, 1, 3) to 100.0,
            LocalDate.of(2024, 2, 4) to 25.0,
            LocalDate.of(2024, 3, 5) to 50.0,
            LocalDate.of(2024, 12, 5) to 15.0,
            LocalDate.of(2024, 12, 6) to 51.0,
            LocalDate.of(2024, 12, 8) to 52.0,
            LocalDate.of(2024, 12, 20) to 55.0,
            LocalDate.of(2024, 12, 22) to 33.0,
            LocalDate.of(2024, 12, 24) to 75.0,
            LocalDate.of(2024, 12, 19) to 100.0
        )
        emit(data)
    }.flowOn(ioDispatcher)
}

