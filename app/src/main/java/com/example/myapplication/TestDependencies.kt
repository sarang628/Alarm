package com.example.myapplication

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.example.torang_core.navigation.LoginNavigation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject

@Module
@InstallIn(ActivityComponent::class)
abstract class TestDependencies {
    @Binds
    abstract fun provideLoginNavigation(testLoginNavigation: TestLoginNavigation): LoginNavigation
}

class TestLoginNavigation @Inject constructor() : LoginNavigation {
    override fun goLogin(fragmentManager: FragmentManager?) {

    }

    override fun goLogin(context: Context) {

    }
}