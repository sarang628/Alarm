package com.sarang.screen_alarm2

import androidx.lifecycle.*
import com.example.torang_core.data.model.Alarm
import com.example.torang_core.repository.AlarmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(private val alarmRepository: AlarmRepository) :
    ViewModel() {
    private val _alarms = MutableLiveData<ArrayList<Alarm>>()
    val alarms: LiveData<ArrayList<Alarm>> = _alarms

    /** 로그인 여부 */
    val isLogin: LiveData<Boolean> = alarmRepository.isLogin

    /**
     * 알림 불러오기
     */
    fun loadAlarms() {
        viewModelScope.launch {
            try {
                _alarms.postValue(alarmRepository.loadAlarm())
            } catch (e: java.lang.Exception) {
                throw Exception("!!!")
            }
        }
    }
}