package id.my.khanifzyen.projectcataloguemovie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    public static String EXTRA_MOVIE = "extra_movie";
    private TextView tvTitle,tvOverview, tvRating, tvReleaseDate;
    private ImageView imgPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvTitle = (TextView) findViewById(R.id.tv_detail_title);
        tvOverview = (TextView) findViewById(R.id.tv_detail_overview);
        tvRating = (TextView) findViewById(R.id.tv_detail_rating);
        tvReleaseDate = (TextView) findViewById(R.id.tv_detail_release_date);
        MovieData mMovieData = getIntent().getParcelableExtra(EXTRA_MOVIE);
        tvTitle.setText(mMovieData.getMovieTitle());
        tvOverview.setText(mMovieData.getMovieOverview());
        tvReleaseDate.setText(mMovieData.getMovieReleaseDate().toString());
        tvRating.setText(mMovieData.getMovieRating().toString());
    }
}
