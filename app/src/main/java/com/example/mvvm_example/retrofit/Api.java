package com.example.mvvm_example.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    
    @GET("quotes")
    Call<ResponseBody> getQuotesApi();
}
