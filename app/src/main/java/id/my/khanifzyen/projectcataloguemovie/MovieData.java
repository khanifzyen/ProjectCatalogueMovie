package id.my.khanifzyen.projectcataloguemovie;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by khanif on 18/01/18.
 */

public class MovieData implements Parcelable {
    private String movieId, movieTitle, moviePoster,movieOverview, movieRating,movieReleaseDate;

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public void setMovieOverview(String movieOverview) {
        this.movieOverview = movieOverview;
    }

    public String getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(String movieRating) {
        this.movieRating = movieRating;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(String movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.movieId);
        dest.writeString(this.movieTitle);
        dest.writeString(this.moviePoster);
        dest.writeString(this.movieOverview);
        dest.writeString(this.movieRating);
        dest.writeString(this.movieReleaseDate);
    }

    public MovieData() {
    }

    protected MovieData(Parcel in) {
        this.movieId = in.readString();
        this.movieTitle = in.readString();
        this.moviePoster = in.readString();
        this.movieOverview = in.readString();
        this.movieRating = in.readString();
        this.movieReleaseDate = in.readString();
    }

    public static final Parcelable.Creator<MovieData> CREATOR = new Parcelable.Creator<MovieData>() {
        @Override
        public MovieData createFromParcel(Parcel source) {
            return new MovieData(source);
        }

        @Override
        public MovieData[] newArray(int size) {
            return new MovieData[size];
        }
    };
}
