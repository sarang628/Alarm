package com.sarang.screen_alarm2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sarang.screen_alarm2.AlarmUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(
    //private val alarmRepository: AlarmRepository
) :
    ViewModel() {
    private val _alarmUiState = MutableLiveData<AlarmUiState>();
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
        } catch (e: java.lang.Exception) {
            _alarmUiState.postValue(_alarmUiState.value?.copy(isRefreshing = false))
        }
    }
}