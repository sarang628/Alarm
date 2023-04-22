package com.sarang.alarm.uistate

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun testRefreshing(): LiveData<AlarmUiState> {
    return MutableLiveData(
        AlarmUiState(
            isRefreshing = true
        )
    )
}


fun testRefreshing1(lifecycleOwner: LifecycleOwner): LiveData<AlarmUiState> {
    val data: MutableLiveData<AlarmUiState> = MutableLiveData(AlarmUiState())
    lifecycleOwner.lifecycleScope.launch {
        while (true) {
            delay(2000)
            val alarmUiState = data.value!!
            data.postValue(
                alarmUiState.copy(isRefreshing = !alarmUiState.isRefreshing)
            )
        }
    }
    return data
}

fun testErrorMsg(lifecycleOwner: LifecycleOwner): LiveData<AlarmUiState> {
    val data: MutableLiveData<AlarmUiState> = MutableLiveData(AlarmUiState())
    lifecycleOwner.lifecycleScope.launch {
        while (true) {
            delay(2000)
            val alarmUiState = data.value!!
            data.postValue(
                alarmUiState.copy(errorMsg = "오류가 발생하였습니다.")
            )
        }
    }
    return data
}

/**
 * 리프레시 후 오류 발생 테스트
 */
fun testRefreshingAfterErrorMsg(lifecycleOwner: LifecycleOwner): LiveData<AlarmUiState> {
    val data: MutableLiveData<AlarmUiState> = MutableLiveData(AlarmUiState())
    lifecycleOwner.lifecycleScope.launch {
        while (true) {
            delay(1000)
            data.postValue(
                AlarmUiState(isRefreshing = true)
            )
            delay(2000)
            data.postValue(
                AlarmUiState(isRefreshing = false)
            )
            data.postValue(
                AlarmUiState(errorMsg = "오류가 발생하였습니다.")
            )
            delay(2000)
            data.postValue(
                AlarmUiState()
            )
        }
    }
    return data
}




