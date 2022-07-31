package com.sarang.screen_alarm2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sarang.screen_alarm2.databinding.ItemAlarmBinding
import com.example.torang_core.data.model.Alarm

internal class AlarmVH(itemAlarmBinding: ItemAlarmBinding) :
    RecyclerView.ViewHolder(itemAlarmBinding.root) {
    private val itemAlarmBinding: ItemAlarmBinding?
    fun setAlarm(alarm: Alarm?) {
        if (itemAlarmBinding != null) itemAlarmBinding.alarm = alarm
    }

    companion object {
        fun create(parent: ViewGroup, viewModel: AlarmViewModel): AlarmVH {
            val inflate = LayoutInflater.from(parent.context)
            val binding = ItemAlarmBinding.inflate(inflate, parent, false)
            binding.vm = viewModel
            return AlarmVH(binding)
        }
    }

    init {
        this.itemAlarmBinding = itemAlarmBinding
    }
}