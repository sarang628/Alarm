package com.sarang.alarm_test_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.sarang.alarm.compose.AlarmScreen
import com.sarang.alarm.uistate.testAlarmUiState
import com.sryang.torang_repository.repository.LoginRepository
import com.sryang.torang_repository.repository.LoginRepositoryTest
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var repository: LoginRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            LoginRepositoryTest(loginRepository = repository)

            AlarmScreen(profileServerUrl = "http://sarang628.iptime.org:89/profile_images/")
        }
    }
}