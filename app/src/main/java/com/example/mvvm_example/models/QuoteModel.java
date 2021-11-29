package com.example.mvvm_example.models;

public class QuoteModel {
    String quoteId;
    String quoteText;
    String quoteAuthor;
    String quoteGenre;

    public QuoteModel() {
    }

    public QuoteModel(String quoteId, String quoteText, String quoteAuthor, String quoteGenre) {
        this.quoteId = quoteId;
        this.quoteText = quoteText;
        this.quoteAuthor = quoteAuthor;
        this.quoteGenre = quoteGenre;
    }

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    public String getQuoteText() {
        return quoteText;
    }

    public void setQuoteText(String quoteText) {
        this.quoteText = quoteText;
    }

    public String getQuoteAuthor() {
        return quoteAuthor;
    }

    public void setQuoteAuthor(String quoteAuthor) {
        this.quoteAuthor = quoteAuthor;
    }

    public String getQuoteGenre() {
        return quoteGenre;
    }

    public void setQuoteGenre(String quoteGenre) {
        this.quoteGenre = quoteGenre;
    }
}
