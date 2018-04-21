package com.example.abhij.imdb;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by abhij on 24-03-2018.
 */

public class MyApi {

    private static MyApi Instance;
    private UserApi userApi;

    private MyApi()
    {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(" http://api.themoviedb.org/3/") //http://localhost:3000
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userApi = retrofit.create(UserApi.class);
    }

    public static MyApi getInstance()
    {
        if(Instance==null)
        {
            Instance = new MyApi();
        }

        return Instance;
    }

    public UserApi getUserApi() {
        return userApi;
    }
}
