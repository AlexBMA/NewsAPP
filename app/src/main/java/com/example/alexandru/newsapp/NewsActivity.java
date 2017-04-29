package com.example.alexandru.newsapp;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

    public static final String MyPREFERENCES = "MyPrefs";
    private final int ID = 1;
    SharedPreferences sharedpreferences;
    private String newUrl = "http://content.guardianapis.com/search?";
    private String key = "3ca5b220-376f-4bc4-b5f9-ca8840c0cef9";
    private ArrayAdapter<Article> articleAdapter;
    private int nrOfItems = 10;
    private String sectionOfArticle = "world";
    private int sw = 0;
    private int sw2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);



        // Find a reference to the {@link ListView} in the layout
        ListView newsListView = (ListView) findViewById(R.id.list);

        //create the adapter
        articleAdapter = new ArticleAdapter(this, new ArrayList<Article>());

        //set the adapter to the list
        newsListView.setAdapter(articleAdapter);


        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Article theArticle = articleAdapter.getItem(position);
                Intent showFullArticle = new Intent(Intent.ACTION_VIEW, Uri.parse(theArticle.getUrl()));
                startActivity(showFullArticle);

            }
        });

        //create the loader
        LoaderManager loaderManager = getLoaderManager();
        //init the loader
        loaderManager.initLoader(ID, null, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Log.e("RESUME","##$$");


        // sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        //  sw = sharedpreferences.getInt("sw",1);
        // sw2 = sharedpreferences.getInt("sw2",1);
        // sectionOfArticle = sharedpreferences.getString("section","world");
        Log.e("SW RESUME", sw + "");

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.restartLoader(ID, null, this);

    }

    @Override
    public void onPause() {
        super.onPause();  // Always call the superclass method first
        // Log.e("PAUSE","##");

        //  sw = 1;
        //  sw2 = 1;
        Log.e("SW PAUSE", sw + "");
        //  SharedPreferences.Editor editor = sharedpreferences.edit();
        //   editor.putInt("sw", sw);
        //  editor.putString("section",sectionOfArticle);
        //  editor.putInt("sw2",sw2);
        //   editor.commit();





    }



    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    public Loader<List<Article>> onCreateLoader(int id, Bundle args) {

        Log.e("LOADER_CREATE", "  ##");

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        String minResults = sharedPrefs.getString(
                getString(R.string.settings_min_results_key),
                getString(R.string.settings_min_results_default));

        String section = sharedPrefs.getString(
                getString(R.string.setting_section_key),
                "world");

        // Log.e("SW", sw + "");

        if (sw == 0) nrOfItems = Integer.parseInt(minResults);
        sectionOfArticle = section;


        // Log.e("NR ITEM", nrOfItems + "");
        // Log.e("SECTION", section);


        Uri baseUri = Uri.parse(newUrl);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("show-elements", "image");
        uriBuilder.appendQueryParameter("show-fields", "all");
        uriBuilder.appendQueryParameter("page-size", nrOfItems + "");
        uriBuilder.appendQueryParameter("section", sectionOfArticle);
        uriBuilder.appendQueryParameter("api-key", key);

        return new ArticleLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Article>> loader, List<Article> data) {
        Log.e("LOADER_FINISHED", "  ##");
        articleAdapter.clear();


        if (data != null && !data.isEmpty()) {
            articleAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Article>> loader) {
        articleAdapter.clear();
    }


    /**
     * Adds more news items to the view
     */
    public void showMoreNews(View v) {

        nrOfItems = nrOfItems + 10;
        sw = 1;
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.restartLoader(ID, null, this);


    }


    /**
     * Menu options creates the menu activity
     *
     * @param menu
     * @return
     */
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
