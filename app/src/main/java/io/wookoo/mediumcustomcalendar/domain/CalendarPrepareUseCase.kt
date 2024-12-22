package io.wookoo.mediumcustomcalendar.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.YearMonth

class CalendarPrepareUseCase(
    private val ioDispatcher: CoroutineDispatcher,
    private val fakeDataBaseRepo: IFakeDataBaseRepo
) {
    suspend operator fun invoke(): CalendarDataInfo {
        return withContext(ioDispatcher) {
            // Fetching data from the database (it may be empty)
            val data: Map<LocalDate, Double> = fakeDataBaseRepo.loadFakeCalendarData().first()

            // Finding the earliest date in the data
            val firstDate = data.keys.minOrNull() ?: LocalDate.now()

            // Converting the first date into year and month
            val (year, month) = firstDate.run { this.year to this.monthValue }

            // Checking if the date is valid
            val isFirstDateValid = year > 0 && month in 1..12

            // If the date is valid, use it; otherwise, use the current month
            val startMonth: YearMonth = if (isFirstDateValid) {
                YearMonth.of(year, month)
            } else {
                YearMonth.now()
            }

            val endMonth: YearMonth = YearMonth.now().plusMonths(1)
            val months: List<YearMonth> = generateSequence(startMonth) { it.plusMonths(1) }
                .takeWhile { it <= endMonth }
                .toList()

            // Finding the index of the current month in the list
            val currentMonthIndex: Int = months.indexOf(YearMonth.now())

            // Returning the prepared data
            CalendarDataInfo(
                currentMonthIndex = currentMonthIndex,
                months = months
            )
        }
    }
}
