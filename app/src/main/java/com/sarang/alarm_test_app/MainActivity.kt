package com.sarang.alarm_test_app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.sarang.alarm.fragment.test
import com.sarang.alarm.uistate.testAlarmUiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state = testAlarmUiState(this@MainActivity, this@MainActivity)
            test(state = state)
        }
    }
}