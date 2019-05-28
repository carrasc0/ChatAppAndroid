package com.example.mvvmtest.ui.activity

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_INDEFINITE
import androidx.core.app.ActivityCompat
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmtest.R
import com.example.mvvmtest.ui.fragment.DiscoverFragment
import com.example.mvvmtest.ui.fragment.MatchFragment
import com.example.mvvmtest.ui.fragment.ProfileFragment
import com.example.mvvmtest.util.Constant
import com.example.mvvmtest.util.ToolbarStatus
import com.example.mvvmtest.viewmodel.MainActivityViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar.LENGTH_LONG
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var discoverFragment: DiscoverFragment? = null
    private var matchFragment: MatchFragment? = null
    private var profileFragment: ProfileFragment? = null
    private var fragmentManager: FragmentManager? = null
    private var activeFragment: Fragment? = null
    private lateinit var itemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener
    private var viewModel: MainActivityViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        initFragments()
    }

    override fun onStart() {
        super.onStart()
        if (!checkPermissions()) {
            requestPermissions()
        } else {
            getLastLocation()
        }
    }

    private fun initFragments() {

        discoverFragment = DiscoverFragment.newInstance()
        matchFragment = MatchFragment.newInstance()
        profileFragment = ProfileFragment.newInstance()
        fragmentManager = supportFragmentManager
        activeFragment = discoverFragment

        itemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_discover -> {
                    fragmentManager!!.beginTransaction().hide(activeFragment!!).show(discoverFragment!!).commit()
                    activeFragment = discoverFragment
                    updateToolbar(ToolbarStatus.DISCOVER)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.action_messages -> {
                    fragmentManager!!.beginTransaction().hide(activeFragment!!).show(matchFragment!!).commit()
                    activeFragment = matchFragment
                    updateToolbar(ToolbarStatus.MATCH)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.action_profile -> {
                    fragmentManager!!.beginTransaction().hide(activeFragment!!).show(profileFragment!!).commit()
                    activeFragment = profileFragment
                    updateToolbar(ToolbarStatus.PROFILE)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

        this.bottomNavigation!!.setOnNavigationItemSelectedListener(itemSelectedListener)
        fragmentManager!!.beginTransaction().add(R.id.mainActivityContainer, profileFragment!!, "3").hide(profileFragment!!).commit()
        fragmentManager!!.beginTransaction().add(R.id.mainActivityContainer, matchFragment!!, "2").hide(matchFragment!!).commit()
        fragmentManager!!.beginTransaction().add(R.id.mainActivityContainer, discoverFragment!!, "1").commit()

    }

    private fun updateToolbar(status: ToolbarStatus) {
        when (status) {

            ToolbarStatus.DISCOVER -> {
                this.toolbarTextView.text = "Discover"
                this.rightIconToolbar.visibility = View.VISIBLE
                this.leftIconToolbar.visibility = View.VISIBLE
            }
            ToolbarStatus.MATCH -> {
                this.leftIconToolbar.visibility = View.INVISIBLE
                this.rightIconToolbar.visibility = View.INVISIBLE
                this.toolbarTextView.text = "Messages"
            }
            ToolbarStatus.PROFILE -> {
                this.leftIconToolbar.visibility = View.INVISIBLE
                this.rightIconToolbar.visibility = View.INVISIBLE
                this.toolbarTextView.text = "Profile"
            }

        }
    }

    private fun checkPermissions() =
            ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

    private fun requestPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, ACCESS_COARSE_LOCATION)) {
            // Provide an additional rationale to the user. This would happen if the user denied the
            // request previously, but didn't check the "Don't ask again" checkbox.
            Log.i(TAG, "Displaying permission rationale to provide additional context.")
            showSnackbar(R.string.permission_rationale, android.R.string.ok, View.OnClickListener {
                // Request permission
                startLocationPermissionRequest()
            })

        } else {
            // Request permission. It's possible this can be auto answered if device policy
            // sets the permission in a given state or the user denied the permission
            // previously and checked "Never ask again".
            Log.i(TAG, "Requesting permission")
            startLocationPermissionRequest()
        }
    }

    private fun showSnackbar(
            snackStrId: Int,
            actionStrId: Int = 0,
            listener: View.OnClickListener? = null) {
        val snackbar = Snackbar.make(findViewById(android.R.id.content), getString(snackStrId),
                LENGTH_LONG)
        if (actionStrId != 0 && listener != null) {
            snackbar.setAction(getString(actionStrId), listener)
        }
        snackbar.show()
    }

    private fun startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(this, arrayOf(ACCESS_COARSE_LOCATION),
                Constant.Others.REQUEST_PERMISSIONS_REQUEST_CODE)
    }

    /**
     * Provides a simple way of getting a device's location and is well suited for
     * applications that do not require a fine-grained location and that do not need location
     * updates. Gets the best and most recent location currently available, which may be null
     * in rare cases when a location is not available.
     *
     * Note: this method should be called after location permission has been granted.
     */
    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        fusedLocationClient.lastLocation.addOnCompleteListener(this) { task: Task<Location> ->
            if (task.isSuccessful && task.result != null) {
                val result = task.result
                if (result != null) {
                    val latitude = result.latitude
                    val longitude = result.longitude
                    Snackbar.make(findViewById(android.R.id.content), "Lat : $latitude Long : $longitude", LENGTH_INDEFINITE).show()
                    Log.d(TAG, "Lat : $latitude Long : $longitude")
                    viewModel!!.setCoordinates(latitude.toString(), longitude.toString())
                } else {
                    Log.d(TAG, "result is null")
                }
            }
        }
    }
}
