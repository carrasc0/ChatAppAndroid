package com.example.mvvmtest.ui.fragment

import android.content.Intent
import androidx.lifecycle.ViewModelProviders

import android.os.Bundle
import androidx.fragment.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.mvvmtest.R

import butterknife.ButterKnife
import com.example.mvvmtest.ui.activity.ChatActivity
import com.example.mvvmtest.ui.activity.EditProfileActivity
import com.example.mvvmtest.ui.activity.SettingsActivity
import com.example.mvvmtest.util.Constant
import com.example.mvvmtest.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.profile_fragment.*

class ProfileFragment : Fragment() {

    private var mViewModel: ProfileViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.profile_fragment, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewClickListener = View.OnClickListener { view ->
            when (view.id) {
                R.id.settingsTextView -> performSettingsIntent()
                R.id.editProfileTextView -> performEditIntent()
            }
        }
        settingsTextView.setOnClickListener(viewClickListener)
        editProfileTextView.setOnClickListener(viewClickListener)
    }

    private fun performSettingsIntent() {
        val settingIntent = Intent(context, SettingsActivity::class.java)
        startActivity(settingIntent)
    }

    private fun performEditIntent() {
        val settingIntent = Intent(context, ChatActivity::class.java)
        settingIntent.putExtra("nickname", 2)
        startActivity(settingIntent)
    }

    companion object {

        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }


}
