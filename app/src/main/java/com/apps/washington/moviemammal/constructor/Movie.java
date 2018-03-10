package com.apps.washington.moviemammal.constructor;

/**
 * Created by Brent on 3/5/2018.
 */

/**
 * Constructor class for the Movie object
 */
public class Movie {

    // TODO Find a way to show details in web browser after clicking a movie

    /**
     * The title of the movie
     */
    private String mMovieTitle;

    /**
     * The vote average for the movie according to "The Movie Database" (This is
     * temporary. It'll be replaced with rating)
     */
    private String mMovieVoteAverage;

    /**
     * The constructor class for the {@link Movie} object
     *
     * @param movieTitle The title of the movie
     * @param movieVoteAverage The vote average of the movie
     */
    public Movie(String movieTitle, String movieVoteAverage) {
        mMovieTitle = movieTitle;
        mMovieVoteAverage = movieVoteAverage;
    }

    // Get the movie title
    public String getMovieTitle() {
        return mMovieTitle;
    }

    // Get the movie's vote average
    public String getMovieVoteAverage() {
        return mMovieVoteAverage;
    }
}
