package com.sarang.alarm.fragment

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sarang.alarm.recyclerview.Alarm
import com.sarang.alarm.uistate.AlarmUiState
import kotlinx.coroutines.flow.StateFlow
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun test(state: StateFlow<AlarmUiState>) {
    val pullRefreshState = rememberPullRefreshState(false, { })
    val data by state.collectAsState()
    Box(
        Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        val pullRefreshState = rememberPullRefreshState(false, { })
        LazyColumn {
            items(data.list.size) {
                Alarm(
                    data.list[it]
                )
            }
        }

        PullRefreshIndicator(
            refreshing = false,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}