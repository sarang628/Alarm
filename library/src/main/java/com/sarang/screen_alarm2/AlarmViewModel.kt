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
    private var _isLoaded = MutableLiveData(false)
    private var isLoaded: LiveData<Boolean> = _isLoaded
    private val _alarms = MutableLiveData<ArrayList<Alarm>>()
    val alarms: LiveData<ArrayList<Alarm>> = _alarms

    /** 로그인 여부 */
    val isLogin: LiveData<Boolean> = alarmRepository.isLogin

    val hasAlarm1 = MediatorLiveData<Boolean>()

    init {
        hasAlarm1.addSource(isLoaded) {
            hasAlarm1.value = handleHasAlarm()
        }
        hasAlarm1.addSource(_alarms) {
            hasAlarm1.value = handleHasAlarm()
        }
    }

    private fun handleHasAlarm(): Boolean {
        return !(_isLoaded.value == true && _alarms.value?.size == 0)
    }

    /**
     * 알림 불러오기
     */
    suspend fun loadAlarms() {
        try {
            _isLoaded.postValue(true)
            _alarms.postValue(alarmRepository.loadAlarm())
        } catch (e: java.lang.Exception) {
            throw Exception(e.message)
        }
    }

    fun hasAlarm(): LiveData<Boolean> {
        if (_isLoaded.value == true && _alarms.value?.size == 0) {
            return MutableLiveData(false);
        }
        return MutableLiveData(true);
    }

}