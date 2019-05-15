package com.example.mvvmtest.kotlin

import androidx.lifecycle.ViewModelProviders

import android.os.Bundle
import androidx.fragment.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.mvvmtest.R

import butterknife.ButterKnife

class ProfileFragment : Fragment() {

    private var mViewModel: ProfileViewModel? = null

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

    companion object {

        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }

}
