package com.example.viewmodeltest.fragments;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.viewmodeltest.R;
import com.example.viewmodeltest.adapter.UserListAdapter;
import com.example.viewmodeltest.data.UserViewModel;

public class ListFragment extends Fragment {

    private UserViewModel mViewModel;
    private ListView listView;

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        listView = (ListView) view.findViewById(R.id.user_list);
        mViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        mViewModel.getUsersLiveData().observe(requireActivity(),users -> {
            listView.setAdapter(new UserListAdapter(getActivity(),R.layout.user_list_layout,users));
        });


        return view;
    }



}