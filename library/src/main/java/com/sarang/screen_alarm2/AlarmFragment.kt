package com.sarang.screen_alarm2

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.torang_core.navigation.LoginNavigation
import com.sarang.screen_alarm2.databinding.FragmentAlarmBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * [AlarmRvAdt]
 * [AlarmVH]
 * [FragmentAlarmBinding]
 */
@AndroidEntryPoint
open class AlarmFragment : Fragment() {

    /** 알림 뷰모델  */
    private val mViewModel: AlarmViewModel by viewModels()

    @Inject
    lateinit var loginNavigation: LoginNavigation

    /** 뷰 생성  */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAlarmBinding.inflate(layoutInflater, container, false)
        binding.viewModel = mViewModel
        binding.loginNavigation = loginNavigation
        binding.lifecycleOwner = viewLifecycleOwner
        binding.rvAlarm.adapter = AlarmRvAdt(mViewModel)
        binding.rvAlarm.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.top = 16
                outRect.bottom = 16
                outRect.left = 8
                outRect.right = 8
            }
        })
        binding.slAlarm.setOnRefreshListener { mViewModel.loadAlarms() }
        initEventActions(binding) // 이벤트 액션 초기화

        return binding.root
    }

    /** 이벤트 엑션 초기화  */
    private fun initEventActions(binding: FragmentAlarmBinding) {
        mViewModel.alarms.observe(viewLifecycleOwner) { alarms ->
            binding.slAlarm.isRefreshing = false
            (binding.rvAlarm.adapter as AlarmRvAdt).setAlarm(alarms)
        }
    }

}