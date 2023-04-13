package com.sarang.screen_alarm2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sarang.screen_alarm2.databinding.ItemAlarmBinding

internal class AlarmVH(itemAlarmBinding: ItemAlarmBinding) :
    RecyclerView.ViewHolder(itemAlarmBinding.root) {
    private val itemAlarmBinding: ItemAlarmBinding?
    fun setAlarm(alarm: AlarmListItem?) {
        if (itemAlarmBinding != null) itemAlarmBinding.alarm = alarm
    }

    companion object {
        fun create(parent: ViewGroup): AlarmVH {
            val inflate = LayoutInflater.from(parent.context)
            val binding = ItemAlarmBinding.inflate(inflate, parent, false)
            return AlarmVH(binding)
        }
    }

    init {
        this.itemAlarmBinding = itemAlarmBinding
    }
}