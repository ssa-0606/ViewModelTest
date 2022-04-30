package com.example.viewmodeltest.data;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.viewmodeltest.pojo.User;
import com.example.viewmodeltest.tasks.MyDataLoad;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class UserViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final MutableLiveData<List<User>> usersLiveData = new MutableLiveData<>();
    private final MutableLiveData<User> userItemLiveData = new MutableLiveData<>();

    public UserViewModel() {
        Log.d("TAG", "UserViewModel:我看看我被实例化的时候被调用了吗？ ");
        findUserDataFromNet();
    }

    public MutableLiveData<List<User>> getUsersLiveData(){
        return usersLiveData;
    }

    public MutableLiveData<User> getUserItemLiveData(){
        return userItemLiveData;
    }

    public void setUsersLiveData(User user){
        userItemLiveData.setValue(user);
    }

    public void findUserDataFromNet(){
        CompletableFuture<List<User>> data = MyDataLoad.getData("http://iteye.cc:3008/stus");
        data.thenAccept(users -> {
           usersLiveData.postValue(users);
           userItemLiveData.postValue(users.get(0));
        });
    }




}