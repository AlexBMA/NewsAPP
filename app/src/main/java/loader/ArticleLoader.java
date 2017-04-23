package loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

import model.Article;
import services.ArticleService;

/**
 * Created by Alexandru on 4/23/2017.
 */

public class ArticleLoader extends AsyncTaskLoader<List<Article>> {

    private String url;

    public ArticleLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {

        Log.e("url in start", url);
        forceLoad();
    }


    @Override
    public List<Article> loadInBackground() {

        ArticleService articleService = new ArticleService();

        return articleService.getListOfArticle(this.url);
    }
}
