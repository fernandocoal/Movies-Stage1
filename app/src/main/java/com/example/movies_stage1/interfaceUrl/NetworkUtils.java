package com.example.movies_stage1.interfaceUrl;

import android.net.Uri;
import android.util.Log;

import com.example.movies_stage1.BuildConfig;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * These utilities will be used to communicate with the weather servers.
 */

public class NetworkUtils {
    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String POPULAR_BASE_URL ="https://api.themoviedb.org/3/movie/popular?";
    private static final String RATE_BASE_URL ="https://api.themoviedb.org/3/movie/top_rated?";
    final static String APPID_PARAM = "api_key";
    final static String APIKEY= BuildConfig.API_KEY;
    final static String LANGUAGE_PARAM = "language";
    private final static String language="en";


    public static URL buildUrlPopular() {
        Uri builtUri = Uri.parse(POPULAR_BASE_URL).buildUpon()
                .appendQueryParameter(APPID_PARAM, APIKEY)
                .appendQueryParameter(LANGUAGE_PARAM, language)
                .build();

        URL url = null;

        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }
    public static URL buildUrlTopRated() {

        Uri builtUri = Uri.parse(RATE_BASE_URL).buildUpon()
                .appendQueryParameter(APPID_PARAM, APIKEY)
                .appendQueryParameter(LANGUAGE_PARAM, language)
                .build();

        URL url = null;

        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}

