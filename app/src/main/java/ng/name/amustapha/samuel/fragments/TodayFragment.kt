package ng.name.amustapha.samuel.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_schedules.*

import ng.name.amustapha.samuel.R
import ng.name.amustapha.samuel.adapters.ScheduleAdapter
import ng.name.amustapha.samuel.adapters.TodayAdapter
import ng.name.amustapha.samuel.databases.Schedule
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class TodayFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedules, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val calendar = Calendar.getInstance()
        val schdls = Schedule.find(Schedule::class.java, " DOM = ? OR DOW = ? OR DATE = ? ", calendar.get(Calendar.DAY_OF_MONTH).toString(), calendar.get(Calendar.DAY_OF_WEEK).toString(), calendar.get(Calendar.DATE).toString())
        schedules.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        schedules.adapter = TodayAdapter(fragmentManager, schdls)
    }
}// Required empty public constructor
