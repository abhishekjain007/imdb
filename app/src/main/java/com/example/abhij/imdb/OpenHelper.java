package com.example.abhij.imdb;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by abhij on 24-03-2018.
 */

public class OpenHelper  {

    private static  OpenHelper Instance;
    private UserApi userApi;

    private OpenHelper()
    {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(" http://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userApi = retrofit.create(UserApi.class);
    }

    public static OpenHelper getInstance()
    {
        if(Instance==null)
        {
            Instance = new OpenHelper();
        }

        return Instance;
    }

    public UserApi getUserApi() {
        return userApi;
    }
}
