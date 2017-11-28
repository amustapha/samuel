package ng.name.amustapha.samuel.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.contact_row.*
import kotlinx.android.synthetic.main.fragment_contact.*

import ng.name.amustapha.samuel.R
import ng.name.amustapha.samuel.utils.Contact
import ng.name.amustapha.samuel.utils.CustomListAdapter


/**
 * A simple [Fragment] subclass.
 */
class ContactFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataSet : ArrayList<Contact> = ArrayList()
        dataSet.add(Contact(R.drawable.whatsapp, "07062688316", "whatsapp://send?text=message"))
        dataSet.add(Contact(R.drawable.facebook, "samuelnoah", url = "http://facebook.com/samuelnoah"))
        dataSet.add(Contact(R.drawable.instagram, "samflexine", "ig"))
        dataSet.add(Contact(R.drawable.twitter, "samuelakoredenoah", "ig"))

        val adapter = CustomListAdapter(activity, dataSet)
        social_links.adapter = adapter
    }

}// Required empty public constructor
