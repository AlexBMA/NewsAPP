package com.example.alexandru.newsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import adapter.AtricleAdpater;
import model.Article;

public class NewsActivity extends AppCompatActivity {


    private ArrayAdapter<Article> articleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        String testRequestString = "http://content.guardianapis.com/search?rights=developer-community&page-size=30&show-rights=developer-community&show-fields=all&order-by=newest&q=France%20or%20football&api-key=3ca5b220-376f-4bc4-b5f9-ca8840c0cef9";

        //list of placeholder data
        List<Article> list = createList(35);

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);


        articleAdapter = new AtricleAdpater(this, list);


        earthquakeListView.setAdapter(articleAdapter);


    }


    private List<Article> createList(int size) {
        List<Article> list = new ArrayList<>();


        for (int i = 1; i < size; i++) {
            Article t1 = new Article();
            t1.setUrl("www.google.com");
            t1.setDateOfArticle(new Date());
            t1.setTitle("Title " + i);
            t1.setAuthor("Author " + i);
            t1.setSectionName("Section " + i);
            list.add(t1);
        }


        return list;
    }



}
