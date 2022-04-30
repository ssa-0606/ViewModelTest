package com.example.viewmodeltest.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.viewmodeltest.R;
import com.example.viewmodeltest.data.UserViewModel;

public class DetailFragment extends Fragment {

    private ImageView imageView ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment, container, false);

        imageView = (ImageView) view.findViewById(R.id.user_img);

        UserViewModel userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        userViewModel.getUserItemLiveData().observe(getViewLifecycleOwner(), user -> {
            Log.d("TAG111", "onCreateView: "+user.getAvatar());
            Glide.with(requireActivity()).load(user.getAvatar()).into(imageView);
        });

        return view;
    }
}
