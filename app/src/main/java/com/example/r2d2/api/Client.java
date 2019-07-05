package com.example.r2d2.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    private static final String BASE_URL="http://api.themoviedb.org/3/";


    public static Retrofit.Builder getRetrofitInstance(){
            return new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    }

    public static Service getService()
    {
        return getRetrofitInstance().build().create(Service.class);
    }

}
