package com.example.mvvmtest.ui.fragment

import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import android.os.Bundle
import androidx.fragment.app.Fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

import com.example.mvvmtest.R
import com.example.mvvmtest.dagger.component.ApiController
import com.example.mvvmtest.interfaces.OnUserViewDiscoverFragmentActionListener
import com.example.mvvmtest.model.DiscoverUser
import com.example.mvvmtest.viewmodel.DiscoverViewModel

import butterknife.BindView
import butterknife.ButterKnife
import com.example.mvvmtest.util.*
import com.example.mvvmtest.viewmodel.MatchViewModel

class DiscoverFragment : Fragment(), OnUserViewDiscoverFragmentActionListener {

    private lateinit var viewModel: DiscoverViewModel
    private var isFirst = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApiController.getAppComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.discover_fragment, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initStateFragment(StateFragment.LOADING);
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
        //initStateFragment(StateFragment.EMPTY);
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(DiscoverViewModel::class.java)

        viewModel.getDiscoverUser()
        viewModel.users.observe(this, Observer { discoverUsers ->
            Log.d("GBC", "entro aqui initview model discover fragment")
            if (!isFirst) {
                isFirst = true
                initDiscoverFragment(discoverUsers[0])
            } else {
                initReplacementFragment(discoverUsers[0])
            }
        })
    }

    override fun onUserAction(idUser: Int, action: DiscoverAction) {
        viewModel.onUserAction(idUser, action)
    }

    private fun initStateFragment(state: StateFragment) {
        val stateFragment = DiscoverStateFragment.newInstance()
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.discoverContainer, stateFragment)
        transaction.commit()
    }

    private fun initDiscoverFragment(user: DiscoverUser) {
        val discoverFragment = UserViewDiscoverFragment.newInstance(user)
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.discoverContainer, discoverFragment)
        transaction.commit()
    }

    private fun initReplacementFragment(user: DiscoverUser) {
        val discoverFragment = UserViewDiscoverFragment.newInstance(user)
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.discoverContainer, discoverFragment)
        transaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.commit()
    }

    companion object {


        fun newInstance(): DiscoverFragment {
            return DiscoverFragment()
        }
    }


}
