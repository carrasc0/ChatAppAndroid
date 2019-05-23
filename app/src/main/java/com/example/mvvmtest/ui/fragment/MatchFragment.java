package com.example.mvvmtest.ui.fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.mvvmtest.R;
import com.example.mvvmtest.adapter.MatchAdapter;
import com.example.mvvmtest.model.Match;
import com.example.mvvmtest.viewmodel.MatchViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MatchFragment extends Fragment {

    private MatchViewModel viewModel;
    private MatchAdapter adapter;

    @BindView(R.id.matchRecyclerView)
    protected RecyclerView recyclerView;

    @BindView(R.id.containerFragment)
    protected FrameLayout frameStates;

    public static MatchFragment newInstance() {
        return new MatchFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.messages_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(MatchViewModel.class);
        viewModel.init();
        initRecyclerView();
        viewModel.getMatch().observe(this, new Observer<List<Match>>() {
            @Override
            public void onChanged(List<Match> matches) {
                if (matches != null) {
                    adapter.notifyDataSetChanged();
                } else {
                    Log.d("GBC", "items is null");
                }
            }
        });

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MatchAdapter(viewModel.getMatch().getValue());
        recyclerView.setAdapter(adapter);
    }

}
