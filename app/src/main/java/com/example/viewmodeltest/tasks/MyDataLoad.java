package com.example.viewmodeltest.tasks;


import com.example.viewmodeltest.pojo.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyDataLoad  {


    public static CompletableFuture<List<User>> getData(String url){

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        CompletableFuture<List<User>> future = CompletableFuture.supplyAsync(()->{
            List<User> userList = null;
            try {
                Response response = client.newCall(request).execute();
                String result = response.body().string();
                JSONArray jsonArray = new JSONArray(result);
                userList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String id = jsonObject.getString("id");
                    String uname = jsonObject.getString("uname");
                    userList.add(new User(Long.valueOf(id),uname,"http://johnyu.cn/dev_g1/photos/"+id+".jpg"));
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return userList;
        });
        return future;
    }



}
