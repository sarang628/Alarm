package com.sarang.alarm.uistate

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.example.library.JsonToObjectGenerator
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.reflect.Type
import java.util.Objects


fun testAlarmUiState(context: Context, lifecycleOwner: LifecycleOwner): StateFlow<AlarmUiState> {
    val data = MutableStateFlow<AlarmUiState>(AlarmUiState())
    lifecycleOwner.lifecycleScope.launch {
        val delay = 10000L
        while (true) {
            //리프레시 테스트
//            data.postValue(testRefreshingOn()); delay(delay); data.postValue(testRefreshingOff()); delay(delay);
            //에러메세지 테스트
//            data.postValue(testErrorOn()); delay(delay); data.postValue(testErrorOff()); delay(delay);
            //리스트 테스트
            data.emit(testListOn(context)); delay(delay);  //data.postValue(testListOff()); delay(delay);
        }
    }
    return data;
}

fun testRefreshingOn(): AlarmUiState {
    return AlarmUiState(isRefreshing = true)
}

fun testRefreshingOff(): AlarmUiState {
    return AlarmUiState(isRefreshing = false)
}

fun testErrorOn(): AlarmUiState {
    return AlarmUiState(errorMsg = "에러 테스트")
}

fun testErrorOff(): AlarmUiState {
    return AlarmUiState()
}

fun testListOn(context: Context): AlarmUiState {
    return AlarmUiState(
        list = JsonToObjectGenerator<AlarmListItem>()
            .getListByFile(context, "alarmList.json", AlarmListItem::class.java)
    )
}

fun testListOff(): AlarmUiState {
    return AlarmUiState()
}