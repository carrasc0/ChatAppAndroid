package com.example.mvvmtest.ui.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import android.os.Bundle
import com.example.mvvmtest.R
import com.example.mvvmtest.ui.fragment.DiscoverFragment
import com.example.mvvmtest.ui.fragment.MatchFragment
import com.example.mvvmtest.ui.fragment.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private var discoverFragment: DiscoverFragment? = null
    private var matchFragment: MatchFragment? = null
    private var profileFragment: ProfileFragment? = null
    private var fragmentManager: FragmentManager? = null
    private var activeFragment: Fragment? = null
    private var itemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragments()
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
                    return@OnNavigationItemSelectedListener true
                }
                R.id.action_messages -> {
                    fragmentManager!!.beginTransaction().hide(activeFragment!!).show(matchFragment!!).commit()
                    activeFragment = matchFragment
                    return@OnNavigationItemSelectedListener true
                }
                R.id.action_profile -> {
                    fragmentManager!!.beginTransaction().hide(activeFragment!!).show(profileFragment!!).commit()
                    activeFragment = profileFragment
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
}
