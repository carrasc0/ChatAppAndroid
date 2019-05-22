package com.example.mvvmtest.ui.fragment;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.mvvmtest.R;
import com.example.mvvmtest.dagger.component.ApiController;
import com.example.mvvmtest.interfaces.OnUserViewDiscoverFragmentActionListener;
import com.example.mvvmtest.model.DiscoverUser;
import com.example.mvvmtest.util.DiscoverAction;
import com.example.mvvmtest.util.StateFragment;
import com.example.mvvmtest.viewmodel.DiscoverViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiscoverFragment extends Fragment implements OnUserViewDiscoverFragmentActionListener {

    @BindView(R.id.discoverContainer)
    protected FrameLayout discoverContainer;

    private DiscoverViewModel mViewModel;
    private boolean isFirst = false;


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
        //initStateFragment(StateFragment.LOADING);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViewModel();
        //initStateFragment(StateFragment.EMPTY);
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(DiscoverViewModel.class);
        mViewModel.init();
        mViewModel.getDiscoverUsers().observe(this, new Observer<List<DiscoverUser>>() {
            @Override
            public void onChanged(List<DiscoverUser> discoverUsers) {
                Log.d("GBC", "entro aqui initview model discover fragment");
                if (!isFirst) {
                    isFirst = true;
                    initDiscoverFragment(discoverUsers.get(0));
                } else {
                    initReplacementFragment(discoverUsers.get(0));
                }

            }
        });
    }

    @Override
    public void onUserAction(int idUser, DiscoverAction action) {
        mViewModel.onUserAction(idUser, action);
    }

    private void initStateFragment(StateFragment state) {
        Fragment stateFragment = DiscoverStateFragment.newInstance();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.discoverContainer, stateFragment);
        transaction.commit();
    }

    private void initDiscoverFragment(DiscoverUser user) {
        Fragment discoverFragment = UserViewDiscoverFragment.newInstance(user);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.discoverContainer, discoverFragment);
        transaction.commit();
    }

    private void initReplacementFragment(DiscoverUser user) {
        Fragment discoverFragment = UserViewDiscoverFragment.newInstance(user);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.discoverContainer, discoverFragment);
        transaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit();
    }


}
