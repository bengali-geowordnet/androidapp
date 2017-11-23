package com.farhanarrafi.geonames.bngeonames.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.farhanarrafi.geonames.bngeonames.R

class AppInfoFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_app_info, container, false)
    }

    companion object {
        /**
         * @return A new instance of fragment AppInfoFragment.
         */
        fun newInstance(): AppInfoFragment {
            val fragment = AppInfoFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
