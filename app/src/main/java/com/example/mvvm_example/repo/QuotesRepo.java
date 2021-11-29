package com.example.mvvm_example.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvm_example.models.QuoteModel;
import com.example.mvvm_example.retrofit.RetrofitClient;
import com.example.mvvm_example.viewmodel.QuotesViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuotesRepo {

    private final Call<ResponseBody> quotesCall;

    public QuotesRepo(int page) {
        quotesCall = RetrofitClient.getInstance().getApi().getQuotesApi(page);
    }

    public MutableLiveData<List<QuoteModel>> getQuotes() {
        final MutableLiveData<List<QuoteModel>> quotes = new MutableLiveData<>();

        quotesCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    JSONObject mainJsonObject = null;
                    List<QuoteModel> quoteModels = new ArrayList<>();

                    try {
                        mainJsonObject = new JSONObject(response.body().string());
                        JSONArray quotesArray = mainJsonObject.optJSONArray("data");
                        for (int i=0;i<quotesArray.length();i++){
                            QuoteModel quoteModel = new QuoteModel();
                            JSONObject quote = quotesArray.optJSONObject(i);
                            quoteModel.setQuoteId(quote.optString("_id"));
                            quoteModel.setQuoteText(quote.optString("quoteText"));
                            quoteModel.setQuoteAuthor(quote.optString("quoteAuthor"));
                            quoteModel.setQuoteGenre(quote.optString("quoteGenre"));
                            quoteModels.add(quoteModel);
                        }
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }

                    quotes.postValue(quoteModels);
                }
                else {
                    try {
                        Log.i("ERROR"," === "+response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

        return quotes;
    }
}
