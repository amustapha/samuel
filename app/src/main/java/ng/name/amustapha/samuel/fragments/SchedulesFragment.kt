package ng.name.amustapha.samuel.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_schedules.*

import ng.name.amustapha.samuel.R
import ng.name.amustapha.samuel.adapters.ScheduleAdapter
import ng.name.amustapha.samuel.databases.Schedule


/**
 * A simple [Fragment] subclass.
 */
class SchedulesFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedules, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        schedules.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        schedules.adapter = ScheduleAdapter(fragmentManager)

    }
}// Required empty public constructor
