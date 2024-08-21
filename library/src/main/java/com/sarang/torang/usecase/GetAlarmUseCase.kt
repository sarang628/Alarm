package com.sarang.torang.usecase

import com.sarang.torang.data1.alarm.AlarmListItem
import kotlinx.coroutines.flow.Flow

interface GetAlarmUseCase {
    suspend fun getAlarm(): List<AlarmListItem>
    val isLogin : Flow<Boolean>
}