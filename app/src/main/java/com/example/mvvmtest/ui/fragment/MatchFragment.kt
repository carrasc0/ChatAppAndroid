package com.example.mvvmtest.ui.fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.mvvmtest.R
import com.example.mvvmtest.adapter.MatchAdapter
import com.example.mvvmtest.model.Match
import com.example.mvvmtest.util.ViewModelFactory
import com.example.mvvmtest.viewmodel.MatchViewModel
import kotlinx.android.synthetic.main.messages_fragment.*

class MatchFragment : Fragment() {

    private lateinit var viewModel: MatchViewModel
    private lateinit var adapter: MatchAdapter
    private var match: Match? = null

    companion object {

        /*fun newInstance(match: Match): MatchFragment {
            val fragment = MatchFragment()
            val args = Bundle()
            args.putParcelable("match", match)
            fragment.arguments = args
            return fragment
        }*/
        fun newInstance(): MatchFragment {
            val fragment = MatchFragment()
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //match = arguments!!.getParcelable("match")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.messages_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
        initRecyclerView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun initViewModel() {
        val viewModelFactory = ViewModelFactory()
        viewModel = ViewModelProviders.of(this.requireActivity(), viewModelFactory)
                .get(MatchViewModel::class.java)
        viewModel.getMatches()
        viewModel.matchLiveData!!.observe(this, Observer { matches ->
            if (matches != null) adapter.notifyDataSetChanged() else Log.d("GBC", "items is null")
        })
    }

    private fun initRecyclerView() {
        matchRecyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = MatchAdapter(viewModel.matchLiveData!!.value!!)
        matchRecyclerView.adapter = adapter
    }


}
