package com.sarang.alarm.recyclerview

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.sarang.alarm.fragment.AlarmListItem

internal class AlarmAdapter :
    RecyclerView.Adapter<ViewHolder>() {
    private var alarms: List<AlarmListItem> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (viewType == 0) {
            return AlarmIndexViewHolder.create(parent)
        } else {
            return AlarmViewHolder.create(parent)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is AlarmViewHolder)
            holder.setAlarm(alarms[position])
        else if(holder is AlarmIndexViewHolder){
            holder.setAlarm(alarms[position].indexDate)
        }
    }

    override fun getItemCount(): Int {
        return alarms.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (alarms[position].indexDate.isNotEmpty()) 0 else 1
    }

    fun setAlarm(list: List<AlarmListItem>) {
        Log.i("AlarmRvAdt", "setAlarm : " + list.size)
        this.alarms = list
        notifyDataSetChanged()
    }
}