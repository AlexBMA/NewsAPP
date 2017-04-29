package com.example.alexandru.newsapp;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import adapter.ArticleAdapter;
import loader.ArticleLoader;
import model.Article;

public class NewsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Article>> {


    private final int ID = 1;
    private final String REQUEST_URL = "http://content.guardianapis.com/search?show-elements=image&show-fields=all&page-size=10&order-by=relevance&q=France&api-key=3ca5b220-376f-4bc4-b5f9-ca8840c0cef9";
    private ArrayAdapter<Article> articleAdapter;

    private int nrOfItems = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);



        // Find a reference to the {@link ListView} in the layout
        ListView newsListView = (ListView) findViewById(R.id.list);


        articleAdapter = new ArticleAdapter(this, new ArrayList<Article>());

        newsListView.setAdapter(articleAdapter);


        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Article theArticle = articleAdapter.getItem(position);
                Intent showFullArticle = new Intent(Intent.ACTION_VIEW, Uri.parse(theArticle.getUrl()));
                startActivity(showFullArticle);

            }
        });


        LoaderManager loaderManager = getLoaderManager();

        loaderManager.initLoader(ID, null, this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.restartLoader(ID, null, this);

    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    public Loader<List<Article>> onCreateLoader(int id, Bundle args) {

        Log.e("LOADER_CREATE", "  ##");

        String newUrl = "http://content.guardianapis.com/search?";
        String key = "3ca5b220-376f-4bc4-b5f9-ca8840c0cef9";
        //http://content.guardianapis.com/search?show-elements=image&show-fields=all&page-size=10&order-by=relevance&q=world&api-key=3ca5b220-376f-4bc4-b5f9-ca8840c0cef9


        Uri baseUri = Uri.parse(newUrl);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("show-elements", "image");
        uriBuilder.appendQueryParameter("show-fields", "all");
        uriBuilder.appendQueryParameter("page-size", nrOfItems + "");
        // uriBuilder.appendQueryParameter("q", "world");
        uriBuilder.appendQueryParameter("api-key", key);

        return new ArticleLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Article>> loader, List<Article> data) {
        Log.e("LOADER_FINISHED", "  ##");
        articleAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (data != null && !data.isEmpty()) {
            articleAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Article>> loader) {
        articleAdapter.clear();
    }


    /**
     * Adds more news items
     */
    public void showMoreNews(View v) {

        nrOfItems = nrOfItems + 10;
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.restartLoader(ID, null, this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, OptionsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
