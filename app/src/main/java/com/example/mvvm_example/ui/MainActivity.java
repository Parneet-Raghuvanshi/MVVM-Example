package com.example.mvvm_example.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.mvvm_example.R;
import com.example.mvvm_example.adapter.QuotesAdapter;
import com.example.mvvm_example.models.QuoteModel;
import com.example.mvvm_example.viewmodel.QuotesViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private QuotesAdapter quotesAdapter;
    private List<QuoteModel> quotes = new ArrayList<>();
    private QuotesViewModel viewModel;
    private CardView progress;
    private ProgressBar progressBar;
    private boolean isScrolling;
    int currentItems, totalItems, scrollOutItems;
    int page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random rand = new Random();
        page = 1 + rand.nextInt((2000 - 1) + 1);

        progress = findViewById(R.id.progress);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        recyclerView = findViewById(R.id.rv_quotes);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        quotesAdapter = new QuotesAdapter(this,quotes);
        recyclerView.setAdapter(quotesAdapter);
        setData(false);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = layoutManager.getChildCount();
                totalItems = layoutManager.getItemCount();
                scrollOutItems = layoutManager.findFirstVisibleItemPosition();

                if(isScrolling && (currentItems + scrollOutItems == totalItems))
                {
                    isScrolling = false;
                    progress.setVisibility(View.VISIBLE);
                    page++;
                    setData(true);
                }
            }
        });
    }

    void setData(boolean flag) {
        viewModel = new ViewModelProvider(this).get(QuotesViewModel.class);
        viewModel.getQuotes(page).observe(this, new Observer<List<QuoteModel>>() {
            @Override
            public void onChanged(List<QuoteModel> quoteModels) {
                if (quoteModels != null){
                    if (flag){
                        progress.setVisibility(View.GONE);
                    }
                    else {
                        progressBar.setVisibility(View.GONE);
                    }
                    quotes.addAll(quoteModels);
                    quotesAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}