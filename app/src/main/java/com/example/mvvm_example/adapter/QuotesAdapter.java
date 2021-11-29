package com.example.mvvm_example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_example.R;
import com.example.mvvm_example.models.QuoteModel;

import java.util.List;

public class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.MyViewHolder>{

    private Context context;
    private List<QuoteModel> quotes;

    public QuotesAdapter(Context context, List<QuoteModel> quotes) {
        this.context = context;
        this.quotes = quotes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.quote_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.quoteText.setText(quotes.get(position).getQuoteText());
        holder.quoteAuthor.setText(quotes.get(position).getQuoteAuthor());
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView quoteText;
        public TextView quoteAuthor;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            quoteText = itemView.findViewById(R.id.quote_text);
            quoteAuthor = itemView.findViewById(R.id.quote_author);
        }
    }
}
