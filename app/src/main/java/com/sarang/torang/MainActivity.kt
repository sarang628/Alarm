package com.sarang.torang

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.google.samples.apps.sunflower.ui.TorangTheme
import com.sarang.torang.repository.LoginRepository
import com.sarang.torang.repository.LoginRepositoryTest
import com.sarang.torang.compose.AlarmScreen
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
                    Column(Modifier.verticalScroll(rememberScrollState())) {
                        Box(Modifier.height(LocalConfiguration.current.screenHeightDp.dp)) {
                            AlarmScreen(onEmailLogin = {}, onProfile = {}, onContents = {})
                        }
                        LoginRepositoryTest(loginRepository = repository)
                    }
                }
            }
        }
    }
}