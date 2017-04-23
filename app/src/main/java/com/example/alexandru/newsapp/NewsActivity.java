package com.example.alexandru.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import adapter.AtricleAdpater;
import model.Article;
import services.ArticleService;

public class NewsActivity extends AppCompatActivity {


    private ArrayAdapter<Article> articleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        String testRequestString = "http://content.guardianapis.com/search?rights=developer-community&page-size=30&show-rights=developer-community&show-fields=all&order-by=newest&q=France%20or%20football&api-key=3ca5b220-376f-4bc4-b5f9-ca8840c0cef9";


        ArticleService articleService = new ArticleService();

        //list of placeholder data
        final List<Article> list = articleService.getListOfArticle();


        // Find a reference to the {@link ListView} in the layout
        ListView newsListView = (ListView) findViewById(R.id.list);


        articleAdapter = new AtricleAdpater(this, list);

        newsListView.setAdapter(articleAdapter);

        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Article theArticle = articleAdapter.getItem(position);
                Intent showFullArticle = new Intent(Intent.ACTION_VIEW, Uri.parse(theArticle.getUrl()));
                startActivity(showFullArticle);

            }
        });


    }


}
