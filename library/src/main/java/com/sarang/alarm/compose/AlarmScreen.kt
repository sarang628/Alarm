package com.sarang.alarm.compose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sarang.alarm.R
import com.sarang.alarm.recyclerview.AlarmListItem
import com.sarang.alarm.viewmodels.AlarmViewModel
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AlarmScreen(
    alarmViewModel: AlarmViewModel = hiltViewModel(),
    profileServerUrl: String,
) {
    val uiState by alarmViewModel.uiState.collectAsState()
    val pullRefreshState = rememberPullRefreshState(uiState.isRefreshing, { })
    Box(
        Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
            .background(color = colorResource(id = R.color.colorSecondaryLight))
    ) {
        LazyColumn {
            Log.d("AlarmScreen", uiState.list.toString())
            val list = uiState.convertDate()
            Log.d("AlarmScreen", list.size.toString())
            items(list.size) {
                if (list[it].indexDate.isNotEmpty()) {
                    AlarmListDateItem(list[it].indexDate)
                } else {
                    AlarmListItem(
                        profileServerUrl = profileServerUrl,
                        alarmListItem = list[it]
                    )
                }
            }
        }

        PullRefreshIndicator(
            refreshing = uiState.isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Composable
fun AlarmListDateItem(text: String) {
    Row(
        Modifier
            .height(50.dp)
            .padding(start = 10.dp, bottom = 10.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            modifier = Modifier,
            text = text,
            fontSize = 24.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
        )
    }
}