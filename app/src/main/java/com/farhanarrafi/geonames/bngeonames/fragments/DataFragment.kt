package com.farhanarrafi.geonames.bngeonames.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.farhanarrafi.geonames.bngeonames.R

class DataFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_data, container, false)
    }

    companion object {
        /**
         * @return A new instance of fragment DataFragment.
         */
        fun newInstance(): DataFragment {
            val fragment = DataFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
