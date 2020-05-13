package com.example.movies_stage1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.movies_stage1.adapter.myAdapter;
import com.example.movies_stage1.interfaceUrl.NetworkUtils;
import com.example.movies_stage1.interfaceUrl.OpenMovieJsonUtils;
import com.example.movies_stage1.parsing.Movies;

import java.net.URL;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = null;
    public ProgressBar progressBar;
    public RecyclerView rvMain;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.popular_movies);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);

        loadMovieData();
    }
    private void loadMovieData() {
        new getMovies().execute();
    }

    public class getMovies extends AsyncTask<String, Void, List<Movies>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Movies> doInBackground(String... params) {
            URL movieRequestPopularityUrl = NetworkUtils.buildUrlPopular();
            try {
                String jsonMovieResponse = NetworkUtils
                        .getResponseFromHttpUrl(movieRequestPopularityUrl);

                List<Movies> simpleJsonMovieData = OpenMovieJsonUtils
                        .getSimpleMovieStringsFromJson(jsonMovieResponse);

                return simpleJsonMovieData;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(final List<Movies> simpleJsonMovieData) {
            progressBar.setVisibility(View.INVISIBLE);
            if (simpleJsonMovieData != null) {
                generateMovieList(simpleJsonMovieData);
            } else {
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public class getTopRatedMovies extends AsyncTask<String, Void, List<Movies>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Movies> doInBackground(String... params) {
            URL movieRequestPopularityUrl = NetworkUtils.buildUrlTopRated();
            try {
                String jsonMovieResponse = NetworkUtils
                        .getResponseFromHttpUrl(movieRequestPopularityUrl);

                List<Movies> simpleJsonMovieData = OpenMovieJsonUtils
                        .getSimpleMovieStringsFromJson(jsonMovieResponse);

                return simpleJsonMovieData;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(final List<Movies> simpleJsonMovieData) {
            progressBar.setVisibility(View.INVISIBLE);
            if (simpleJsonMovieData != null) {
                generateMovieList(simpleJsonMovieData);
            } else {
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void generateMovieList(final List<Movies> simpleJsonMovieData) {
        myAdapter adapter = new myAdapter(this, simpleJsonMovieData, new myAdapter.MovieItemClickListener(){
            @Override
            public void onMovieItemClick(int clickedItemIndex) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("Movie", simpleJsonMovieData.get(clickedItemIndex));
                startActivity(intent);
            }
        });

        initializeGridView(adapter);
    }

    private void initializeGridView(myAdapter adapter) {
        rvMain=(RecyclerView) findViewById(R.id.rv_main);

        rvMain.setHasFixedSize(true);
        rvMain.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        rvMain.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.topRated:

                new getTopRatedMovies().execute();
                setTitle(R.string.toprated_movies);
                break;
            case R.id.popular:

                new getMovies().execute();
                setTitle(R.string.popular_movies);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
