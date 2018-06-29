package com.shakeel.testgithub.networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Shakeel on 06/28/2018.
 */

public class RetrofitClient {

    private ApiService apiService;
    private static RetrofitClient retrofitClient = null;
    private static final String BASE_URL = "https://api.github.com/users/";

    public static RetrofitClient getInstance() {
        if (retrofitClient == null) {
            return retrofitClient = new RetrofitClient();
        } else
            return retrofitClient;

    }

    private RetrofitClient() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public ApiService getApiService() {
        return apiService;
    }
}
