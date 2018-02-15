package id.my.khanifzyen.projectcataloguemovie;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by khanif on 09/01/18.
 */

public class MovieItems {
    private int idMovie;
    private String movieTitle, movieOverview, movieReleaseDate, moviePoster;

    public MovieItems(JSONObject object) {
        try {
            int idMovie = object.getInt("id");
            String originalTitle = object.getString("original_title");
            String overview = object.getString("overview");
            //DATE CONVERSION
            //step 1: capture the string
            String releaseDate = object.getString("release_date");
            //step 2: create simpledateformat object based on formatted string
            DateFormat srcDf = new SimpleDateFormat("yyyy-MM-dd");
            //step 3: convert/parse string to date format based on original format
            Date date = srcDf.parse(releaseDate);
            //step 4: create new simpledateformat object based on new format date
            DateFormat destDf = new SimpleDateFormat("EEE, MMM d, ''yy");
            //step 5: create string format by using SimpleDateFormat above
            releaseDate = destDf.format(date);

            String poster = object.getString("poster_path");

            this.idMovie = idMovie;
            this.movieTitle = originalTitle;
            this.movieOverview = overview;
            this.movieReleaseDate = releaseDate;
            this.moviePoster = poster;

        } catch(Exception e){
            e.printStackTrace();
        }

    }

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public void setMovieOverview(String movieOverview) {
        this.movieOverview = movieOverview;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(String movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }
}
