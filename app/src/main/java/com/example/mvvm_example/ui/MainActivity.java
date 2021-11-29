package com.example.mvvm_example.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.mvvm_example.R;
import com.example.mvvm_example.adapter.QuotesAdapter;
import com.example.mvvm_example.models.QuoteModel;
import com.example.mvvm_example.viewmodel.QuotesViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private QuotesAdapter quotesAdapter;
    private List<QuoteModel> quotes = new ArrayList<>();
    private QuotesViewModel viewModel;
    int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_quotes);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        quotesAdapter = new QuotesAdapter(this,quotes);
        recyclerView.setAdapter(quotesAdapter);

        viewModel = new ViewModelProvider(this).get(QuotesViewModel.class);
        viewModel.getQuotes(page).observe(this, new Observer<List<QuoteModel>>() {
            @Override
            public void onChanged(List<QuoteModel> quoteModels) {
                if (quoteModels != null){
                    quotes.addAll(quoteModels);
                    quotesAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}