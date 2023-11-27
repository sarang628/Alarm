package com.sryang.torang.usecase

import com.sryang.torang.data.AlarmListItem
import kotlinx.coroutines.flow.Flow

interface GetAlarmUseCase {
    suspend fun getAlarm(): List<AlarmListItem>
    val isLogin : Flow<Boolean>
}