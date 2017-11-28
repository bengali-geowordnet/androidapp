package com.farhanarrafi.geonames.bngeonames.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.farhanarrafi.geonames.bngeonames.Constants
import com.farhanarrafi.geonames.bngeonames.Preferences
import com.farhanarrafi.geonames.bngeonames.R
import com.farhanarrafi.geonames.bngeonames.fragments.AppInfoFragment
import com.farhanarrafi.geonames.bngeonames.fragments.DataFragment
import com.farhanarrafi.geonames.bngeonames.fragments.UserInfoFragment
import kotlinx.android.synthetic.main.activity_data_push.*


class DataPushActivity : AppCompatActivity() {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    private lateinit var mainContent : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_push)
        initialize()
    }

    private fun initialize(){
        mainContent = main_content
        checkForPermissions()
        setSupportActionBar(toolbar)
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        container.adapter = mSectionsPagerAdapter

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_data_push, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_settings) {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
        return true
    }

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            when (position) {
                0 -> return AppInfoFragment.newInstance()
                1 -> return UserInfoFragment.newInstance()
                2 -> return DataFragment.newInstance()
                else -> {
                }
            }
            return Fragment()
        }

        override fun getCount(): Int {
            return 3
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            Constants.PERMISSION_FOR_LOCATION -> {
                if (grantResults.isNotEmpty()
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Preferences.setSharedPrefrences(this,
                            Constants.PERMISSION_FOR_LOCATION_GRANTED,true)
                    Snackbar.make(mainContent,getString(R.string.location_persmission_granted),
                            Snackbar.LENGTH_SHORT).show()
                } else {
                    Preferences.setSharedPrefrences(this,
                            Constants.PERMISSION_FOR_LOCATION_GRANTED,false)
                    Snackbar.make(mainContent,getString(R.string.location_permission_denied),
                            Snackbar.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

    private fun checkForPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                Snackbar.make(mainContent, getString(R.string.location_access_rationale),
                        Snackbar.LENGTH_INDEFINITE).setAction(getString(R.string.ok)) {
                    // Request the permission
                    ActivityCompat.requestPermissions(this@DataPushActivity,
                            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                            Constants.PERMISSION_FOR_LOCATION)
                }.show()


            } else {
                Snackbar.make(mainContent,
                        getString(R.string.requesting_location_permission),
                        Snackbar.LENGTH_SHORT).show()
                ActivityCompat.requestPermissions(this,
                        arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION),
                        Constants.PERMISSION_FOR_LOCATION)
            }
        }
    }
}
