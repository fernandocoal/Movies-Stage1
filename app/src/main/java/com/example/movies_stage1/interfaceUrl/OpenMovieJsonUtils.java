package com.example.movies_stage1.interfaceUrl;


import com.example.movies_stage1.parsing.Movies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility functions to handle MovieDB JSON data.
 */

public class OpenMovieJsonUtils {



    /**
     * This method parses JSON from a web response and returns an array of Strings

     * @param forecastJsonStr JSON response from server
     *
     * @return Array of Strings describing weather data
     *
     * @throws JSONException If JSON data cannot be properly parsed
     **/

    public static List<Movies> getSimpleMovieStringsFromJson(String forecastJsonStr)
            throws JSONException {

        /* Movie information. Each movie is an element of the "results" array */
        final String OWM_RESULTS = "results";
        final String OWM_LANGUAGE = "original_language";
        /* Movie title*/
        final String OWM_TITLE = "title";
        /* Movie popularity*/
        final String OWM_POPULARITY = "popularity";
        /* Movie votes*/
        final String OWM_VOTE = "vote_average";
        /* Movie description*/

        final String OWN_ORIGINAL_TITLE="original_title";

        final String OWM_OVERVIEW = "overview";
        /* Movie poster*/
        final String OWM_POSTER_PATH = "poster_path";

        final String OWM_BACK_PATH = "backdrop_path";

        final String OWM_DATE = "release_date";


        JSONObject forecastJson = new JSONObject(forecastJsonStr);
        
        JSONArray movieArray = forecastJson.getJSONArray(OWM_RESULTS);

        int length = movieArray.length();
        List<Movies> movieList = new ArrayList<>(length);

        for (int i = 0; i < movieArray.length(); i++) {
            String date;

            /* These are the values that will be collected */
            double popularity;
            double vote;
            String overview;
            String title;
            String posterPath;
            String back;
            String original;

            /* Get the JSON object representing the movie i */
            JSONObject eachMovie = movieArray.getJSONObject(i);
            overview = eachMovie.getString(OWM_OVERVIEW);
            popularity=eachMovie.getInt(OWM_POPULARITY);
            vote = eachMovie.getDouble(OWM_VOTE);
            String language = eachMovie.getString(OWM_LANGUAGE);
            date = eachMovie.getString(OWM_DATE);
            posterPath = eachMovie.getString(OWM_POSTER_PATH);
            title = eachMovie.getString(OWM_TITLE);
            back=eachMovie.getString(OWM_BACK_PATH);
            original=eachMovie.getString(OWN_ORIGINAL_TITLE);
            Movies movie = new Movies(posterPath,overview,date,title,back, popularity,vote,language,original);

            movie.setOverview(overview);
            movie.setReleaseDate(date);
            movie.setPosterPath(posterPath);
            movie.setPopularity(popularity);
            movie.setTitle(title);
            movie.setVoteAverage(vote);
            movie.setBackdropPath(back);
            movie.setOriginalTitle(original);
            movie.setOriginalLanguage(language);


            movieList.add(movie);
        }

        return movieList;
    }
}
