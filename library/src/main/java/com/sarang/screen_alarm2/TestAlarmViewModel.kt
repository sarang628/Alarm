package com.sarang.screen_alarm2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class TestAlarmViewModel @Inject constructor() :
    ViewModel() {
    private val _alarmUiState = MutableLiveData(AlarmUiState(false, ArrayList(), null));
    val alarmUiState = _alarmUiState;

    /** 로그인 여부 */
    //val isLogin: LiveData<Boolean> = alarmRepository.isLogin

    /**
     * 알림 불러오기
     */
    suspend fun loadAlarms() {
        try {
            /*_alarms.postValue(
                alarmRepository.loadAlarm()
            )*/
            delay(1000)
            _alarmUiState.postValue(
                _alarmUiState.value?.copy(
                    isRefreshing = false,
                    isLoaded = true
                )
            )
        } catch (e: java.lang.Exception) {

        }
    }
}