package com.example.mvvm_example.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_example.models.QuoteModel;
import com.example.mvvm_example.repo.QuotesRepo;

import java.util.List;

public class QuotesViewModel extends ViewModel {
    private MutableLiveData<List<QuoteModel>> quotes;
    private QuotesRepo quotesRepo;

    public QuotesViewModel() {
    }

    public MutableLiveData<List<QuoteModel>> getQuotes(int page) {
        quotesRepo = new QuotesRepo(page);
        quotes = quotesRepo.getQuotes();
        return quotes;
    }
}
