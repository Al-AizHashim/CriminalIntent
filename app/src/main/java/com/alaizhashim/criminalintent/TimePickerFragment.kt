package com.alaizhashim.criminalintent

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*

private const val ARG_TIME = "time"
class TimePickerFragment:DialogFragment() {
    interface Callbacks {
        fun onTimeSelected(time: Date)
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var calendar=Calendar.getInstance()
        val timeListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)
            val resultTime: Date = calendar.time
            targetFragment?.let { fragment ->
                (fragment as Callbacks).onTimeSelected(resultTime)
            }
        }
        var time= arguments?.getSerializable(ARG_TIME) as Date
        calendar.time=time
        val initHourOfDay=calendar.get(Calendar.HOUR_OF_DAY)
        val initMinute=calendar.get(Calendar.MINUTE)
        return TimePickerDialog(requireContext(),timeListener,initHourOfDay,initMinute,false)
    }
    companion object {
        fun newInstance(time: Date): TimePickerFragment {
            val args = Bundle().apply {
                putSerializable(ARG_TIME, time)
            }
            return TimePickerFragment().apply {
                arguments = args
            }
        }
    }

}