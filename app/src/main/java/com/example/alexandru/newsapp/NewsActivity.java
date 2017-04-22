package com.example.alexandru.newsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        String testRequestString = "http://content.guardianapis.com/search?rights=developer-community&page-size=30&show-rights=developer-community&show-fields=all&order-by=newest&q=France%20or%20football&api-key=3ca5b220-376f-4bc4-b5f9-ca8840c0cef9";
    }
}
