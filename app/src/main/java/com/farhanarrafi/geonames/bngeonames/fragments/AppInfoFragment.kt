package com.farhanarrafi.geonames.bngeonames.fragments

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.farhanarrafi.geonames.bngeonames.R
import com.farhanarrafi.geonames.bngeonames.model.Application
import kotlinx.android.synthetic.main.fragment_app_info.view.*
import okhttp3.*
import java.io.IOException

class AppInfoFragment : Fragment() {
    val JSON: MediaType? = MediaType.parse("application/json; charset=utf-8")
    val client = OkHttpClient()
    var url = "https://requestb.in/xh399lxh"

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

            var application = Application(view.et_app_name.text.toString(),
                    view.et_app_email.text.toString(),view.et_app_type.text.toString())
            post(url, application.getJSON())

        }
        return view
    }

    fun post(url: String, json: String) {
        var body: RequestBody = RequestBody.create(JSON, json)
        var request: Request = Request.Builder()
                .url(url)
                .post(body)
                .build()

        client.newCall(request).enqueue(callback);
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

    var callback: Callback = object : Callback {
        override fun onFailure(call: Call?, e: IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: Call?, response: Response?) {
            Log.d("POST", response.toString())
            Snackbar.make(view!!, "response.toString()", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show()
        }
    }
}
