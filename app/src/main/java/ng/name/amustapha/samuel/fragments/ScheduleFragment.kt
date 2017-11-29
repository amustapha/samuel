package ng.name.amustapha.samuel.fragments


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.accessibility.AccessibilityEventCompat.setAction
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import junit.framework.Test
import kotlinx.android.synthetic.main.fragment_add_schedule.*
import me.riddhimanadib.formmaster.FormBuilder
import me.riddhimanadib.formmaster.model.*
import me.riddhimanadib.formmaster.viewholder.FormElementHeader
import ng.name.amustapha.samuel.MainActivity

import ng.name.amustapha.samuel.R
import ng.name.amustapha.samuel.databases.Category
import ng.name.amustapha.samuel.databases.Schedule
import ng.name.amustapha.samuel.utils.Hack


/**
 * A simple [Fragment] subclass.
 */
class ScheduleFragment : Fragment() {

    var schedule: Schedule = Schedule()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_schedule, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = arguments?.getLong("id", 0)
        if (id != null && id > 0){
            schedule = Schedule.findById(Schedule::class.java, id)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val formBuilder = FormBuilder(context, schedule_form)
        val categories = ArrayList<String>()
        for (category in Category.listAll(Category::class.java)){
            categories.add(category.name)
        }
        val category = FormElementPickerSingle.createInstance().setTitle("Category").setHint("Schedule category").setOptions(categories)
        val title = FormElementTextSingleLine.createInstance().setTitle("Activity title").setHint("What would you like to do?")
        val date = FormElementPickerDate.createInstance().setDateFormat("d MMMM, y").setTitle("Date")
        val startTime = FormElementPickerTime.createInstance().setTimeFormat("hh:mm aa").setTitle("Start time").setHint("What time does this activity start")
        val endtime = FormElementPickerTime.createInstance().setTimeFormat("hh:mm aa").setTitle("End time").setHint("What time does this activity end")
        val repeating = FormElementPickerSingle.createInstance().setTitle("Recurring").setHint("Repeat activity?").setOptions(Hack.listFromResource(context, R.array.recurring_types))
        val silent = FormElementPickerSingle.createInstance().setTitle("Silent?").setHint("Silent phone").setOptions(Hack.listFromResource(context, R.array.silent_options))
        val location = FormElementTextSingleLine.createInstance().setTitle("Venue").setHint("Where")

        val formFields = ArrayList<BaseFormElement>()
        formFields.addAll(arrayOf( category, title, date, startTime, endtime, repeating, silent, location))
        formBuilder.addFormElements(formFields)

        if(schedule.title != null)
        {
            category.setValue(schedule.category.name)
            title.setValue(schedule.title)
            date.setValue(schedule.date.toString())
            startTime.setValue("${schedule.startHour}:${schedule.startMinute}")
            endtime.setValue("${schedule.stopHour}:${schedule.stopMinute}")
//            repeating.setValue("${schedule.recurring}")

        }

        save_schedule.setOnClickListener({
            schedule.setCategory(category.value).setTitle(title.value).setStartTime(startTime.value)
                    .setRecurring(Hack.listFromResource(context, R.array.recurring_types).indexOf(repeating.value))
                    .setSilent(Hack.listFromResource(context, R.array.silent_options).indexOf(silent.value))
                    .setStopTime(endtime.value).setDate(date.value).setLocation(location.value)

                schedule.save()
            AlertDialog.Builder(context)
                    .setTitle("Saved")
                    .setMessage("Entries have been saved! Do you want to check your schedueles?")
                    .setPositiveButton("Check", DialogInterface.OnClickListener { dialogInterface, i -> (activity as MainActivity).replace(SchedulesFragment()) })
                    .setNegativeButton("Continue editing", DialogInterface.OnClickListener { dialogInterface, i ->  })
                    .show()
        })
    }




}// Required empty public constructor
