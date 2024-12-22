package io.wookoo.mediumcustomcalendar

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.wookoo.mediumcustomcalendar.presentation.calendar.mvi.AppViewModel
import io.wookoo.mediumcustomcalendar.presentation.calendar.mvi.DateToProgress
import io.wookoo.mediumcustomcalendar.presentation.calendar.ui.CustomCalendar
import io.wookoo.mediumcustomcalendar.presentation.theme.MediumCustomCalendarTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val calendarViewModel = koinViewModel<AppViewModel>()

            val state by calendarViewModel.state.collectAsStateWithLifecycle()
            val monthProgress: DateToProgress = state.dateToProgress
            val calendarDataInfo = state.calendarDataInfo

            MediumCustomCalendarTheme {
                Box(
                    modifier = Modifier.background(DarkGray)
                ) {
                    CustomCalendar(
                        modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars),
                        monthProgress = monthProgress,
                        calendarDataInfo = calendarDataInfo,
                        onDateClick = { localDate, progress ->
                            Toast.makeText(
                                this@MainActivity,
                                "You clicked a date $localDate with progress $progress",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        onErrorClick = {
                            Toast.makeText(
                                this@MainActivity,
                                R.string.no_day_data,
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                    )
                }
            }
        }
    }
}
