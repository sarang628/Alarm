package com.sarang.alarm.uistate

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sarang.alarm.fragment.AlarmListItem
import com.sarang.alarm.fragment.AlarmUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader

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

fun testAlarmList(context: Context, lifecycleOwner: LifecycleOwner): LiveData<AlarmUiState> {
    val data: MutableLiveData<AlarmUiState> = MutableLiveData(AlarmUiState())
    lifecycleOwner.lifecycleScope.launch {
        delay(1000)
        data.postValue(
            AlarmUiState(isRefreshing = true)
        )
        delay(2000)
        data.postValue(
            AlarmUiState(list = getTestAlarmListByFile(context))
        )
    }
    return data
}

fun getTestAlarmListByFile(context: Context): List<AlarmListItem> {
    val inputStream = context.assets.open("alarmList.json")
    val inputStreamReader = InputStreamReader(inputStream)
    val bufferReader = BufferedReader(inputStreamReader)

    val gson = Gson();
    val list = gson.fromJson<List<AlarmListItem>>(
        bufferReader,
        object : TypeToken<List<AlarmListItem>>() {}.type
    )
    return list
}

fun getTestAlarmList(): ArrayList<AlarmListItem> {
    return ArrayList<AlarmListItem>().apply {
        add(
            AlarmListItem(
                0, "리사님이 이 포스트를 좋아합니다.", "2", "1분 전"
            )
        )
        add(
            AlarmListItem(
                0, "리사님이 이 포스트를 좋아합니다.", "2", "5분 전"
            )
        )
        add(
            AlarmListItem(
                0, "리사님이 이 포스트를 좋아합니다.", "2", "1시간 전"
            )
        )
        add(
            AlarmListItem(
                0, "리사님이 이 포스트를 좋아합니다.", "2", "6시간 전"
            )
        )
        add(
            AlarmListItem(
                0, "리사님이 이 포스트를 좋아합니다.", "2", "어제"
            )
        )
    }
}







