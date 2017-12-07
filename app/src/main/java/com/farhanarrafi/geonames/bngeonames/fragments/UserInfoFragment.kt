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
import com.farhanarrafi.geonames.bngeonames.R
import com.farhanarrafi.geonames.bngeonames.model.User
import com.farhanarrafi.geonames.bngeonames.utility.Constants
import com.farhanarrafi.geonames.bngeonames.utility.Preferences
import com.farhanarrafi.geonames.bngeonames.utility.ResponseCallback
import com.farhanarrafi.geonames.bngeonames.utility.Utils
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_user_info.view.*


class UserInfoFragment : Fragment(), ResponseCallback {
    var url: String = ""
    lateinit var tvToken: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
        }
        url = Preferences.get(context, Constants.SERVER_LIST,
                Constants.DEFAULT_SERVER_URL) + Constants.USER_URL
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_user_info, container, false)

        view.user_register_button.text = getString(R.string.register_user)
        tvToken = view.tv_user_token
        view.user_register_button.setOnClickListener {
            val user = User(view.et_user_name.text.toString(), view.et_user_email.text.toString(),
                    view.et_user_phone.text.toString())
            Utils.requestTokenFromServer(url, Gson().toJson(user), this)
        }
        return view
    }

    override fun setResult(result: String) {
        Preferences.set(context, Constants.USER_KEY, result)
        Handler(Looper.getMainLooper()).post({
            tvToken.text = result
        })
        Snackbar.make(view!!, result, Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show()
    }


    companion object {
        /**
         * @return A new instance of fragment UserInfoFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(): UserInfoFragment {
            val fragment = UserInfoFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
