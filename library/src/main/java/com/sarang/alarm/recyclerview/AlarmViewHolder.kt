package com.sarang.alarm.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sarang.alarm.databinding.ItemAlarmBinding
import com.sarang.alarm.uistate.AlarmListItem

internal class AlarmViewHolder(itemAlarmBinding: ItemAlarmBinding) :
    RecyclerView.ViewHolder(itemAlarmBinding.root) {
    var itemAlarmBinding: ItemAlarmBinding
    fun setAlarm(alarm: AlarmListItem?) {
        if (itemAlarmBinding != null) itemAlarmBinding.alarm = alarm
    }

    companion object {
        fun create(parent: ViewGroup): AlarmViewHolder {
            val inflate = LayoutInflater.from(parent.context)
            val binding = ItemAlarmBinding.inflate(inflate, parent, false)
            return AlarmViewHolder(binding)
        }
    }

    init {
        this.itemAlarmBinding = itemAlarmBinding
    }
}