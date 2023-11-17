package com.sarang.alarm_test_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.google.samples.apps.sunflower.ui.TorangTheme
import com.sarang.alarm.compose.AlarmScreen
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
            TorangTheme {
                Surface(
                    Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    Column(
                    ) {
                        Box(Modifier.weight(1f)) {
                            AlarmScreen(profileServerUrl = "http://sarang628.iptime.org:89/profile_images/")
                        }
                        Box(Modifier.weight(1f)) {
                            LoginRepositoryTest(loginRepository = repository)
                        }
                    }
                }
            }
        }
    }
}