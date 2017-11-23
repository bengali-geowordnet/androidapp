package com.farhanarrafi.geonames.bngeonames.fragments

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.farhanarrafi.geonames.bngeonames.R
import kotlinx.android.synthetic.main.fragment_app_info.view.*

class AppInfoFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view :View = inflater!!.inflate(R.layout.fragment_app_info, container, false)

        view.button.text = getString(R.string.register_app)

        view.button.setOnClickListener {
            Snackbar.make(view, "Application Info Sent to Server", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show()
        }

        return view
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
