package com.example.mvvmtest.view.fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.mvvmtest.R;
import com.example.mvvmtest.dagger.component.ApiController;
import com.example.mvvmtest.model.DiscoverUser;
import com.example.mvvmtest.viewmodel.DiscoverViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiscoverFragment extends Fragment {

    @BindView(R.id.stateContainer)
    protected FrameLayout stateContainer;
    @BindView(R.id.discoverContainer)
    protected FrameLayout discoverContainer;

    private DiscoverViewModel mViewModel;


    public static DiscoverFragment newInstance() {
        return new DiscoverFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApiController.getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.discover_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(DiscoverViewModel.class);
        mViewModel.init();
        mViewModel.getDiscoverUsers().observe(this, new Observer<List<DiscoverUser>>() {
            @Override
            public void onChanged(List<DiscoverUser> discoverUsers) {
                //todo nuevos usuarios llegaron, aplicar logica
            }
        });
    }

}
