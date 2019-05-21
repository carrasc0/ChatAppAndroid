package com.example.mvvmtest.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.mvvmtest.R;
import com.example.mvvmtest.ui.fragment.DiscoverFragment;
import com.example.mvvmtest.ui.fragment.MatchFragment;
import com.example.mvvmtest.ui.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottomNavigation)
    protected BottomNavigationView bottomNavigation;

    private DiscoverFragment discoverFragment;
    private MatchFragment matchFragment;
    private ProfileFragment profileFragment;
    private FragmentManager fragmentManager;
    private Fragment activeFragment;
    private BottomNavigationView.OnNavigationItemSelectedListener itemSelectedListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragments();
    }

    private void initFragments() {

        discoverFragment = DiscoverFragment.newInstance();
        matchFragment = MatchFragment.newInstance();
        profileFragment = ProfileFragment.Companion.newInstance();
        fragmentManager = getSupportFragmentManager();
        activeFragment = discoverFragment;

        itemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_discover:
                        fragmentManager.beginTransaction().hide(activeFragment).show(discoverFragment).commit();
                        activeFragment = discoverFragment;
                        return true;
                    case R.id.action_messages:
                        fragmentManager.beginTransaction().hide(activeFragment).show(matchFragment).commit();
                        activeFragment = matchFragment;
                        return true;
                    case R.id.action_profile:
                        fragmentManager.beginTransaction().hide(activeFragment).show(profileFragment).commit();
                        activeFragment = profileFragment;
                        return true;
                }
                return false;
            }
        };

        bottomNavigation.setOnNavigationItemSelectedListener(itemSelectedListener);
        fragmentManager.beginTransaction().add(R.id.mainActivityContainer, profileFragment, "3").hide(profileFragment).commit();
        fragmentManager.beginTransaction().add(R.id.mainActivityContainer, matchFragment, "2").hide(matchFragment).commit();
        fragmentManager.beginTransaction().add(R.id.mainActivityContainer, discoverFragment, "1").commit();

    }
}
