package com.sarang.torang.usecase

import com.sarang.torang.data1.alarm.AlarmListItemUIState
import kotlinx.coroutines.flow.Flow

interface GetAlarmUseCase {
    suspend fun getAlarm(): List<AlarmListItemUIState>
    val isLogin : Flow<Boolean>
}