package id.my.khanifzyen.projectcataloguemovie;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<MovieItems>> {

    ListView listViewMovie;
    MovieAdapter adapter;
    Button btnCari;
    EditText edtMovie;


    static final String EXTRAS_MOVIE = "EXTRAS_MOVIE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MovieAdapter(this);
        adapter.notifyDataSetChanged();
        listViewMovie = (ListView) findViewById(R.id.listViewMovie);

        listViewMovie.setAdapter(adapter);
        edtMovie = (EditText)findViewById(R.id.edt_movie);
        btnCari = (Button) findViewById(R.id.btn_cari);

        btnCari.setOnClickListener(myListener);
        String searchMovie = edtMovie.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_MOVIE,searchMovie);

        listViewMovie.setOnItemClickListener(itemListener);

        getLoaderManager().initLoader(0,bundle,this);
    }

    @Override
    public Loader<ArrayList<MovieItems>> onCreateLoader(int id, Bundle args) {
        String searchMovie = "";
        if(args!=null){
            searchMovie = args.getString(EXTRAS_MOVIE);
        }
        return new MovieAsyncTaskLoader(this,searchMovie);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MovieItems>> loader, ArrayList<MovieItems> data) {
        adapter.setData(data);
        ProgressBar progressbar = (ProgressBar) findViewById(R.id.progress_bar);
        progressbar.setVisibility(View.GONE);

    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MovieItems>> loader) {
        adapter.setData(null);
    }

    View.OnClickListener myListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            String searchMovie = edtMovie.getText().toString();
            if(TextUtils.isEmpty(searchMovie)) return;

            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_MOVIE,searchMovie);
            ProgressBar progressbar = (ProgressBar) findViewById(R.id.progress_bar);
            progressbar.setVisibility(View.VISIBLE);
            getLoaderManager().restartLoader(0,bundle,MainActivity.this);
        }

    };

    AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView idMovie = (TextView) view.findViewById(R.id.tv_id);
        TextView titleMovie = (TextView) view.findViewById(R.id.tv_title);
        TextView overviewMovie = (TextView) view.findViewById(R.id.tv_overview);
        TextView posterMovie = (TextView) view.findViewById(R.id.tv_poster);
        //Toast.makeText(view.getContext(),"id: " + idMovie.getText(), Toast.LENGTH_SHORT).show();
            MovieData mMovieData = new MovieData();
            mMovieData.setMovieId(idMovie.getText().toString());
            mMovieData.setMovieTitle(titleMovie.getText().toString());
            mMovieData.setMovieOverview(overviewMovie.getText().toString());
            mMovieData.setMoviePoster(posterMovie.getText().toString());
            //Toast.makeText(view.getContext(),"title: "+mMovieData.getMovieTitle(),Toast.LENGTH_SHORT).show();
            Intent detailIntent = new Intent(MainActivity.this, DetailActivity.class);
            detailIntent.putExtra(DetailActivity.EXTRA_MOVIE, mMovieData);

            startActivity(detailIntent);
        }
    };
}
