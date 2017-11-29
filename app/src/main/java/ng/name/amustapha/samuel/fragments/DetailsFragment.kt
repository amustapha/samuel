package ng.name.amustapha.samuel.fragments


import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_details.*
import ng.name.amustapha.samuel.MainActivity

import ng.name.amustapha.samuel.R
import ng.name.amustapha.samuel.databases.Category
import ng.name.amustapha.samuel.databases.Schedule
import ng.name.amustapha.samuel.utils.Hack
import java.lang.reflect.Field


/**
 * A simple [Fragment] subclass.
 */
class DetailsFragment : DialogFragment() {

    lateinit var schedule: Schedule
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val id =arguments?.getLong("id")
        if(id!= null && id>0) {
            schedule = Schedule.findById(Schedule::class.java, id)
        }
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataList = ArrayList<Map<String, String>>()
        dataList.add(map("Category", schedule.category.name))
        dataList.add(map("Title", schedule.title))
        dataList.add(map("Date", schedule.getDate().toString()))
        dataList.add(map("Time", "${schedule.startHour}:${schedule.startMinute}"))

        val adapter = SimpleAdapter(context, dataList, android.R.layout.simple_list_item_2, arrayOf("key", "value"), Hack.res())
        information.adapter = adapter

        edit_schedule.setOnClickListener({
            val schedulFrag = ScheduleFragment()
            val args = Bundle()
            args.putLong("id",  schedule.id)
            schedulFrag.arguments  = args
            fragmentManager?.beginTransaction()?.replace(R.id.container, schedulFrag)?.commit()
            dialog.dismiss()
            (activity as MainActivity).softReplace(schedulFrag)

        })

        delete_schedule.setOnClickListener({
            AlertDialog.Builder(context).
                    setTitle("Confirm delete")
                    .setMessage("Are you sure you really want to delete this category? This action cannot be reversed")
                    .setPositiveButton("Delete", DialogInterface.OnClickListener { dialogInterface1, i1 ->
                        schedule.delete()
                        Toast.makeText(context, "Schedule successfully deleted", Toast.LENGTH_LONG).show()
                        (activity as MainActivity).softReplace(TodayFragment())
                        dialog.dismiss()
                    })
                    .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface1, i1 -> /*do nothing */ })
                    .show()
        })
    }

    fun map(key:String, value:String): Map<String, String>{
        val hashMap : HashMap<String, String> = HashMap()
        hashMap.put("key", key)
        hashMap.put("value", value)
        return hashMap
    }

}// Required empty public constructor
