package com.farhanarrafi.geonames.bngeonames.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.farhanarrafi.geonames.bngeonames.*
import com.farhanarrafi.geonames.bngeonames.model.Application
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_app_info.view.*


class AppInfoFragment : Fragment(), ResponseCallback {
    var url: String = ""
    lateinit var tvToken: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
        }
        url = Preferences.get(context, Constants.SERVER_LIST,
                Constants.DEFAULT_SERVER_URL) + Constants.APP_URL
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view: View = inflater!!.inflate(R.layout.fragment_app_info, container, false)

        view.app_register_button.text = getString(R.string.register_app)
        tvToken = view.tv_app_token
        view.app_register_button.setOnClickListener {

            val application = Application(view.et_app_name.text.toString(),
                    view.et_app_email.text.toString(), view.et_app_type.text.toString())
            Utils.requestTokenFromServer(url, Gson().toJson(application), this)
        }
        return view
    }

    override fun setToken(token: String) {
        Preferences.set(context, "APP_KEY", token)
        Handler(Looper.getMainLooper()).post({
            tvToken.text = token
        })
        Snackbar.make(view!!, token, Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show()
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
