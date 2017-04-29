package services;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import model.Article;

/**
 * Created by Alexandru on 4/23/2017.
 */

public class ArticleService {


    private final String LOG_TAG = this.getClass().getName();

    public List<Article> getListOfArticle(String url) {

        if (TextUtils.isEmpty(url)) {
            return null;
        }

        URL urlArticles = null;
        try {
            urlArticles = new URL(url);

        } catch (MalformedURLException e) {

            e.printStackTrace();
        }

        String JSON = createJSONFromUrl(urlArticles);


        List<Article> list = new ArrayList<>();

        try {
            JSONObject root = new JSONObject(JSON);
            root = root.getJSONObject("response");
            JSONArray results = root.getJSONArray("results");

            int size = results.length();
            JSONObject temp;
            JSONObject temp2;
            for (int i = 0; i < size; i++) {
                temp = results.getJSONObject(i);
                Article article = new Article();


                article.setTitle(temp.getString("webTitle"));
                article.setUrl(temp.getString("webUrl"));
                temp2 = temp.getJSONObject("fields");
                article.setQuickInfo(temp2.getString("trailText"));
                //article.setThumnailUrl(temp2.getString("thumbnail"));
                Log.e("date " + i, temp.getString("webTitle"));

                //Date date  = new Date();
                list.add(article);
            }

            // Log.e("&&", list.toString());
            return list;

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return list;
    }

    private String createJSONFromUrl(URL url) {

        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setInstanceFollowRedirects(true);
            urlConnection.connect();

            //if it was successful response code 200
            // the read the input steam
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);

            } else {
                Log.e(LOG_TAG, "STATUS CODE: " + urlConnection.getResponseCode());
            }

            return jsonResponse;

        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.e(LOG_TAG, jsonResponse);

        return jsonResponse;
    }


    private String readFromStream(InputStream inputStream) throws IOException {

        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();


    }
}
