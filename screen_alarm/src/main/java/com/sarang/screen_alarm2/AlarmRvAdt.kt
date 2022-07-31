package com.sarang.screen_alarm2

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.torang_core.data.model.Alarm

internal class AlarmRvAdt constructor(val viewModel: AlarmViewModel) :
    RecyclerView.Adapter<AlarmVH>() {
    private var alarms: List<Alarm> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmVH {
        return AlarmVH.create(parent, viewModel)
    }

    override fun onBindViewHolder(holder: AlarmVH, position: Int) {
        holder.setAlarm(alarms[position])
    }

    override fun getItemCount(): Int {
        return alarms.size
    }

    fun setAlarm(alarms: List<Alarm>) {
        this.alarms = alarms
        notifyDataSetChanged()
    }
}