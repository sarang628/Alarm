package com.sarang.alarm.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sarang.alarm.databinding.ItemIndexBinding
import com.sarang.alarm.fragment.AlarmListItem

internal class AlarmIndexViewHolder(itemIndexBinding: ItemIndexBinding) :
    RecyclerView.ViewHolder(itemIndexBinding.root) {
    private val itemAlarmBinding: ItemIndexBinding?
    fun setAlarm(alarm: AlarmListItem?) {
        //if (itemAlarmBinding != null) itemAlarmBinding.alarm = alarm
    }

    companion object {
        fun create(parent: ViewGroup): AlarmIndexViewHolder {
            val inflate = LayoutInflater.from(parent.context)
            val binding = ItemIndexBinding.inflate(inflate, parent, false)
            return AlarmIndexViewHolder(binding)
        }
    }

    init {
        this.itemAlarmBinding = itemIndexBinding
    }
}