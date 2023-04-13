package com.sarang.screen_alarm2

import android.app.AlertDialog
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.sarang.screen_alarm2.databinding.FragmentAlarmListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [AlarmRvAdt]
 * [AlarmVH]
 * [FragmentAlarmListBinding]
 */
@AndroidEntryPoint
open class AlarmListFragment : Fragment() {

    /** 알림 뷰모델  */
    private val viewModel: TestAlarmViewModel by viewModels()

    /** 알람 내비게이션 */
//    @Inject
//    lateinit var loginNavigation: LoginNavigation

    lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAlarmListBinding.inflate(layoutInflater, container, false)
        // 뷰 초기화
        initView(binding)
        // 스와이프 레이아웃 리프레시
        binding.slAlarm.setOnRefreshListener {
            loadAlarm()
        }
        // 뷰모델 구독
        subScribeViewModel(viewModel, binding)
        return binding.root
    }

    private fun initView(binding: FragmentAlarmListBinding) {
        swipeRefreshLayout = binding.slAlarm
        binding.lifecycleOwner = viewLifecycleOwner
        binding.rvAlarm.adapter = AlarmRvAdt()
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
    }

    private fun loadAlarm() {
        viewModel.viewModelScope.launch {
            viewModel.loadAlarms()
        }
    }

    /** 이벤트 엑션 초기화  */
    private fun subScribeViewModel(
        viewModel: TestAlarmViewModel,
        binding: FragmentAlarmListBinding
    ) {
        viewModel.alarmUiState.observe(viewLifecycleOwner) { uistate ->
            Log.i("AlarmListFragment", uistate.toString())
            binding.slAlarm.isRefreshing = uistate.isRefreshing
            (binding.rvAlarm.adapter as AlarmRvAdt).setAlarm(uistate.list)
            binding.uiState = uistate
        }

        /*viewModel.isLogin.observe(viewLifecycleOwner) {
            if (!it) {
                findNavController().navigate(R.id.nonLoginFragment)
            }
        }*/
    }

}