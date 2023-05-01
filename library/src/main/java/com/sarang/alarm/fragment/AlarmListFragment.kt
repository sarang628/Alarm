package com.sarang.alarm.fragment

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.text.style.ClickableSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.sarang.alarm.databinding.FragmentAlarmListBinding
import com.sarang.alarm.recyclerview.AlarmAdapter
import com.sarang.alarm.recyclerview.AlarmRecyclerViewItemDecoration
import com.sarang.alarm.uistate.AlarmUiState
import com.sarang.alarm.uistate.testAlarmUiState
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

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

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        /*observeUiState(
            testAlarmList(requireContext(), viewLifecycleOwner), viewDataBinding
        )*/
        observeUiState(
            testAlarmUiState(requireContext(), viewLifecycleOwner), viewDataBinding
        )
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initView(binding: FragmentAlarmListBinding) {
        binding.lifecycleOwner = viewLifecycleOwner

        // 리사이클러뷰 설정
        binding.rvAlarm.adapter = AlarmAdapter(
            clickUser = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    Toast.makeText(requireContext(), "clickUser", Toast.LENGTH_SHORT).show()
                }
            },
            clickPost = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    Toast.makeText(requireContext(), "clickPost", Toast.LENGTH_SHORT).show()
                }
            },
            clickImage = {
                Toast.makeText(requireContext(), "clickImage", Toast.LENGTH_SHORT).show()
            }
        )
        binding.rvAlarm.addItemDecoration(AlarmRecyclerViewItemDecoration())


        // 스와이프 레이아웃 리프레시
        binding.slAlarm.setOnRefreshListener {
            //TODO:: 알림 갱신
        }

        // 리사이클러뷰 하단 도달 시 추가 알림 요청
        binding.rvAlarm.addOnScrollListener(object : OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (
                    !recyclerView.canScrollVertically(1)
                    && newState == RecyclerView.SCROLL_STATE_IDLE
                ) {
                    Toast.makeText(requireContext(), "Last", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.N)
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
            (binding.rvAlarm.adapter as AlarmAdapter).setAlarm(uiState.convertDate())

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