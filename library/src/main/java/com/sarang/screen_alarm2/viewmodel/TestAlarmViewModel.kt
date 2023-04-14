package com.sarang.screen_alarm2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarang.screen_alarm2.AlarmUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestAlarmViewModel @Inject constructor() :
    ViewModel() {
    private val _alarmUiState = MutableLiveData(AlarmUiState(false, ArrayList(), null));
    val alarmUiState = _alarmUiState;

    /** 로그인 여부 */
    val isLogin: LiveData<Boolean> = MutableLiveData(true)

    /**
     * 알림 불러오기
     */
    fun loadAlarms() {
        viewModelScope.launch {
            try {
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
}