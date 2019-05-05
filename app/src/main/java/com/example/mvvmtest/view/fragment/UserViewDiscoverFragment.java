package com.example.mvvmtest.view.fragment;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvvmtest.R;
import com.example.mvvmtest.viewmodel.UserViewDiscoverViewModel;

public class UserViewDiscoverFragment extends Fragment {

    private UserViewDiscoverViewModel mViewModel;

    public static UserViewDiscoverFragment newInstance() {
        return new UserViewDiscoverFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_view_discover_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(UserViewDiscoverViewModel.class);
        // TODO: Use the ViewModel
    }

}
