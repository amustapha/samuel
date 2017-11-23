package ng.name.amustapha.samuel.fragments


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_category.*

import ng.name.amustapha.samuel.R
import ng.name.amustapha.samuel.databases.Category


/**
 * A simple [Fragment] subclass.
 */
class CategoriesFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categories = ArrayList<Map<String, String>>()
        for (category in Category.listAll(Category::class.java)) {
            val map = HashMap<String, String>()
            map.put("id", category.id.toString())
            map.put("title", category.name)
            map.put("body", category.description)

            categories.add(map)
        }


        val adapter = SimpleAdapter(context, categories, android.R.layout.simple_list_item_2, arrayOf("title", "body"), intArrayOf(android.R.id.text1, android.R.id.text2))
        category_list.adapter = adapter
        category_list.setOnItemClickListener { adapterView, innerView, position, xid ->

            AlertDialog.Builder(context)
                    .setTitle("Action")
                    .setMessage("What action would you like to perform?")
                    .setPositiveButton("Edit", DialogInterface.OnClickListener { dialogInterface, i ->
                        val frag = CategoryFragment()
                        val args = Bundle()
                        args.putLong("id", categories.get(position).get("id")!!.toLong())
                        frag.arguments = args
                        fragmentManager?.beginTransaction()?.replace(R.id.container, frag)?.commitNow()
                    })
                    .setNegativeButton("Delete", DialogInterface.OnClickListener { dialogInterface, i ->
                        AlertDialog.Builder(context).
                                setTitle("Confirm delete")
                                .setMessage("Are you sure you really want to delete this category? This action cannot be reversed")
                                .setPositiveButton("Delete", DialogInterface.OnClickListener { dialogInterface1, i1 ->
                                    Category.findById(Category::class.java, categories.get(position).get("id")!!.toLong()).delete()
                                    Toast.makeText(context, "Category successfully deleted", Toast.LENGTH_LONG).show()
                                })
                                .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface1, i1 -> /*do nothing */ })
                                .show()
                    })
                    .setNeutralButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i -> /*do nothing */ })
                    .show()
        }

    }

}