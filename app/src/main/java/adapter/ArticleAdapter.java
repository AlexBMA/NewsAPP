package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.alexandru.newsapp.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.Article;

/**
 * Created by Alexandru on 4/23/2017.
 */

public class ArticleAdapter extends ArrayAdapter<Article> {


    public ArticleAdapter(Context context, List<Article> list) {
        super(context, 0, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View listItemView = convertView;

        // Check if the existing view is being reused, otherwise inflate the view
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_item, parent, false);
        }

        // Get the current article
        Article theArticle = getItem(position);

        // get the text view
        TextView titleTextView = (TextView) listItemView.findViewById(R.id.article_title);
        titleTextView.setText(theArticle.getTitle());


        // get the text view
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.article_date);
        dateTextView.setText(simpleDateFormat(theArticle));

        // get the text view
        TextView articleAuthorTextView = (TextView) listItemView.findViewById(R.id.article_author);
        articleAuthorTextView.setText(theArticle.getAuthor());

        // get the text view
        TextView articleSectionTextView = (TextView) listItemView.findViewById(R.id.article_section);
        articleSectionTextView.setText(theArticle.getSectionName());

        //get the text view
        TextView articleQuickTextTextView = (TextView) listItemView.findViewById(R.id.article_quick_text);
        articleQuickTextTextView.setText((theArticle.getQuickInfo()));

        //get the img view
        //ImageView articleThumbnail = (ImageView) listItemView.findViewById(R.id.article_img);
        //articleThumbnail.setImageURI(Uri.parse(theArticle.getThumnailUrl()));


        return listItemView;
    }

    private String simpleDateFormat(Article currentArticle) {
        SimpleDateFormat sdf = new SimpleDateFormat("LLL dd, yyyy");
        Date tempDate = currentArticle.getDateOfArticle();

        return sdf.format(tempDate);
    }


}
