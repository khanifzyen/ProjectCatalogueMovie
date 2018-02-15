package id.my.khanifzyen.projectcataloguemovie;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by khanif on 09/01/18.
 */

public class MovieAsyncTaskLoader extends AsyncTaskLoader<ArrayList<MovieItems>> {
    private ArrayList<MovieItems> mData;
    private boolean mHasResult = false;
    private String movieSearch;

    public MovieAsyncTaskLoader(Context context, String movieSearch) {
        super(context);
        onContentChanged();
        this.movieSearch = movieSearch;
    }

    @Override
    protected void onStartLoading() {
        //super.onStartLoading();
        if(takeContentChanged())
            forceLoad();
        else if (mHasResult)
            deliverResult(mData);
    }

    @Override
    public void deliverResult(ArrayList<MovieItems> data) {
        mData = data;
        mHasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if(mHasResult){
            onReleaseResources(mData);
            mData = null;
            mHasResult = false;
        }
    }

    private static final String API_KEY = "b1e697a1b1231592866bb539bd70dfa8";

    @Override
    public ArrayList<MovieItems> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();
        final ArrayList<MovieItems> movieItemses = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/search/movie?api_key="+API_KEY+"&language=en-US&query="+movieSearch;
        client.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for(int i=0;i<list.length();i++){
                        JSONObject movie = list.getJSONObject(i);
                        MovieItems movieItems = new MovieItems(movie);
                        movieItemses.add(movieItems);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                //do nothing
            }
        });
        return movieItemses;
    }

    private void onReleaseResources(ArrayList<MovieItems> data){
        //donoting
    }

}
