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
import kotlinx.android.synthetic.main.fragment_category.*
import me.riddhimanadib.formmaster.FormBuilder
import me.riddhimanadib.formmaster.model.*
import me.riddhimanadib.formmaster.viewholder.FormElementHeader

import ng.name.amustapha.samuel.R
import ng.name.amustapha.samuel.R.id.save_schedule
import ng.name.amustapha.samuel.R.id.schedule_form
import ng.name.amustapha.samuel.databases.Category
import ng.name.amustapha.samuel.databases.Schedule
import ng.name.amustapha.samuel.utils.Hack


/**
 * A simple [Fragment] subclass.
 */
class CategoryFragment : Fragment() {

    var category = Category()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_schedule, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = arguments?.getLong("id", 0)
        if (id != null && id > 0){
            category = Category.findById(Category::class.java, id)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val formBuilder = FormBuilder(context, schedule_form)

        val name = FormElementTextSingleLine.createInstance().setTitle("Name").setHint("Category name")
        val description= FormElementTextMultiLine.createInstance().setTitle("Description").setHint("Category description")

        val formFields = ArrayList<BaseFormElement>()
        formFields.addAll(arrayOf( name, description))
        formBuilder.addFormElements(formFields)

        if(category.name != null)
        {
            name.setValue(category.name)
            description.value = category.description
        }

        save_schedule.setOnClickListener({
            category = Category(name.value, description.value)
            category.save()
        })


    }


}// Required empty public constructor
