package com.example.mvvmtest.view.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mvvmtest.R;
import com.example.mvvmtest.interfaces.OnUserViewDiscoverFragmentActionListener;
import com.example.mvvmtest.model.DiscoverUser;
import com.example.mvvmtest.util.DiscoverAction;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserViewDiscoverFragment extends Fragment {

    @BindView(R.id.image1DU)
    protected ImageView image1;
    @BindView(R.id.image2DU)
    protected ImageView image2;
    @BindView(R.id.image3DU)
    protected ImageView image3;
    @BindView(R.id.zodiacDU)
    protected ImageView zodiac;
    @BindView(R.id.nameDU)
    protected TextView name;
    @BindView(R.id.cityDU)
    protected TextView city;
    @BindView(R.id.homeTownDU)
    protected TextView hometown;
    @BindView(R.id.professionDU)
    protected TextView profession;
    @BindView(R.id.jobDU)
    protected TextView job;
    @BindView(R.id.heightDU)
    protected TextView height;
    @BindView(R.id.noideaDU)
    protected TextView noIdea;
    @BindView(R.id.drinkDU)
    protected TextView drink;
    @BindView(R.id.smokeDU)
    protected TextView smoking;
    @BindView(R.id.btnDislike)
    protected FloatingActionButton btnDislike;
    @BindView(R.id.btnlike)
    protected FloatingActionButton btnLike;
    @BindView(R.id.btnILoveIt)
    protected FloatingActionButton btnIloveIt;

    private DiscoverUser user;
    private OnUserViewDiscoverFragmentActionListener onActionListener;

    public static UserViewDiscoverFragment newInstance(DiscoverUser user) {
        UserViewDiscoverFragment fragment = new UserViewDiscoverFragment();
        Bundle args = new Bundle();
        args.putParcelable("user", user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = getArguments().getParcelable("user");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_view_discover_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        processUser();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getParentFragment() instanceof OnUserViewDiscoverFragmentActionListener) {
            onActionListener = (OnUserViewDiscoverFragmentActionListener) getParentFragment();
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnUserViewDiscoverFragmentActionListener");
        }
    }

    private void processUser() {
        processImages();
        processText();
    }

    private void processImages() {
        List<String> images = user.getImages();
        if (images == null) {
            return;
        }
        //todo hacer logica para la cantidad de imagenes que bajaron
        //todo procesar zodiaco
        Glide.with(getContext())
                .asBitmap()
                .load(images.get(0))
                .into(image1);

        Glide.with(getContext())
                .asBitmap()
                .load(images.get(1))
                .into(image2);

        Glide.with(getContext())
                .asBitmap()
                .load(images.get(2))
                .into(image3);
    }

    private void processText() {
        name.setText(user.getCity() + " " + user.getAge());
        city.setText(user.getCity());
        hometown.setText(user.getHometown());
        profession.setText(user.getProfession());
        job.setText(user.getJob());
        height.setText(user.getHeight());
        drink.setText(user.getSmoke().name());
        smoking.setText(user.getSmoke().name());
    }

    @OnClick(R.id.btnILoveIt)
    void IloveItClicked(){
        onActionListener.onUserAction(user.getIdUser(), DiscoverAction.I_LOVE_IT);
    }


}
