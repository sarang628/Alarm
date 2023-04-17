package com.sarang.alarm.fragment

import android.app.AlertDialog
import android.app.UiAutomation
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.sarang.alarm.databinding.FragmentAlarmListBinding
import com.sarang.alarm.recyclerview.AlarmAdapter
import com.sarang.alarm.recyclerview.AlarmRecyclerViewItemDecoration
import com.sarang.alarm.uistate.testAlarmList
import com.sarang.alarm.uistate.testErrorMsg
import com.sarang.alarm.uistate.testRefreshing1
import com.sarang.alarm.uistate.testRefreshingAfterErrorMsg
import dagger.hilt.android.AndroidEntryPoint

/** 알림 UiState */
data class AlarmUiState(
    val isRefreshing: Boolean = false,
    val list: List<AlarmListItem> = ArrayList(),
    val errorMsg: String? = null,
    val isLoaded: Boolean = false,
    val isLogin: Boolean = false
) {
    fun hasAlarm(): Boolean {
        if (isLoaded && list.isEmpty()) {
            return false
        }
        return true
    }
}

/** 알림 리스트 데이터 */
data class AlarmListItem(
    val id: Int,
    val contents: String,
    val otherPictureUrl: String,
    val createDate: String
)

@AndroidEntryPoint
open class AlarmListFragment : Fragment() {

    val TAG = "AlarmListFragment"

    lateinit var viewDataBinding: FragmentAlarmListBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentAlarmListBinding.inflate(layoutInflater, container, false)
        // 뷰 초기화
        initView(viewDataBinding)

        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeUiState(
            testAlarmList(requireContext(), viewLifecycleOwner), viewDataBinding
        )
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initView(binding: FragmentAlarmListBinding) {
        binding.lifecycleOwner = viewLifecycleOwner

        // 리사이클러뷰 설정
        binding.rvAlarm.adapter = AlarmAdapter()
        binding.rvAlarm.addItemDecoration(AlarmRecyclerViewItemDecoration())

        // 스와이프 레이아웃 리프레시
        binding.slAlarm.setOnRefreshListener {
            //TODO:: 알림 갱신
        }
    }

    private fun observeUiState(
        alarmUiState: LiveData<AlarmUiState>,
        binding: FragmentAlarmListBinding
    ) {
        val alertDialog: AlertDialog = activity.let {
            val builder = AlertDialog.Builder(it)
            builder.create()
        }

        alarmUiState.observe(viewLifecycleOwner) { uiState ->
            Log.i(TAG, uiState.toString())
            // 스와이프 리프레시 상태 변경
            binding.slAlarm.isRefreshing = uiState.isRefreshing

            // 리사이클러뷰 보여주는 유무
            binding.rvAlarm.visibility = if (uiState.hasAlarm()) View.VISIBLE else View.GONE

            // 텍스트 보여주는 유무
            binding.tvEmpty.visibility = if (uiState.hasAlarm()) View.GONE else View.VISIBLE

            // 새로 받은 알람 설정
            (binding.rvAlarm.adapter as AlarmAdapter).setAlarm(uiState.list)

            // 비 로그인 상태라면 화면이동
            if (!uiState.isLogin) {
                //findNavController().navigate(R.id.nonLoginFragment)
            }

            if (uiState.errorMsg != null) {
                if (!alertDialog.isShowing) {
                    alertDialog.setMessage(uiState.errorMsg)
                    alertDialog.show()
                }
            } else {
                if (alertDialog.isShowing)
                    alertDialog.dismiss()
            }
        }
    }
}