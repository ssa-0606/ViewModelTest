package com.example.viewmodeltest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.viewmodeltest.R;
import com.example.viewmodeltest.data.UserViewModel;
import com.example.viewmodeltest.fragments.DetailFragment;
import com.example.viewmodeltest.pojo.User;
import com.example.viewmodeltest.tasks.MyDataLoad;

import java.util.List;

public class UserListAdapter extends ArrayAdapter<User> {

    private int resourceId;

    public UserListAdapter(@NonNull Context context, int resource, @NonNull List<User> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        User item = getItem(position);
        View view = LayoutInflater.from(parent.getContext()).inflate(resourceId, null);
        TextView textView = (TextView) view.findViewById(R.id.user_name);
        Button button = (Button) view.findViewById(R.id.user_btn);
        textView.setText(item.getName());
        button.setOnClickListener(view1 -> {
            new ViewModelProvider((ViewModelStoreOwner) getContext()).get(UserViewModel.class).setUsersLiveData(item);
            AppCompatActivity appCompatActivity = (AppCompatActivity) getContext();
            Fragment fragment = appCompatActivity.getSupportFragmentManager().findFragmentById(R.id.fragment_contain);
            appCompatActivity.getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_contain, DetailFragment.class,null)
                    .hide(fragment)
                    .addToBackStack(null)
                    .commit();
        });

        return view;
    }
}
