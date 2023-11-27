package com.sryang.torang.usecase

import com.sryang.torang.uistate.AlarmListItem
import kotlinx.coroutines.flow.Flow

interface GetAlarmUseCase {
    suspend fun getAlarm(): List<AlarmListItem>
    val isLogin : Flow<Boolean>
}