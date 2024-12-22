package io.wookoo.mediumcustomcalendar.di

import io.wookoo.mediumcustomcalendar.data.FakeDataBaseRepoImpl
import io.wookoo.mediumcustomcalendar.domain.CalendarPrepareUseCase
import io.wookoo.mediumcustomcalendar.domain.IFakeDataBaseRepo
import io.wookoo.mediumcustomcalendar.presentation.calendar.mvi.AppViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val calendarModule = module {
    single<CoroutineDispatcher>(named("IO")) { Dispatchers.IO }
    single<CalendarPrepareUseCase> {
        CalendarPrepareUseCase(
            ioDispatcher = get(named("IO")),
            fakeDataBaseRepo = get()
        )
    }
    single<IFakeDataBaseRepo> { FakeDataBaseRepoImpl(ioDispatcher = get(named("IO"))) }
    viewModelOf(::AppViewModel)
}