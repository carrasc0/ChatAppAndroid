package com.example.mvvmtest.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.ImageView;

import com.example.mvvmtest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditProfileActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.imageViewBlue)
    protected ImageView image1;
    @BindView(R.id.imageViewRed)
    protected ImageView image2;
    @BindView(R.id.imageViewGreen)
    protected ImageView image3;
    @BindView(R.id.imageViewYellow)
    protected ImageView image4;
    @BindView(R.id.imageViewOrange)
    protected ImageView image5;
    @BindView(R.id.imageViewPink)
    protected ImageView image6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);


    }

}
