package com.example.movies_stage1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.movies_stage1.parsing.Movies;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;


public class DetailsActivity extends AppCompatActivity {

    private ImageView moviePoster;
    private TextView movieTitle;
    private TextView movieLanguage;
    private TextView moviePlot;
    private TextView movieReleaseDate;
    private TextView movieVoteAverage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().hide();
        moviePoster= (ImageView) findViewById(R.id.iv_details_moviePoster);
        movieTitle= (TextView) findViewById(R.id.tv_details_MovieTitle);
        movieLanguage= (TextView) findViewById(R.id.tv_details_Language);
        moviePlot= (TextView) findViewById(R.id.tv_details_plot);
        movieReleaseDate= (TextView) findViewById(R.id.tv_details_releaseDate);
        movieVoteAverage= (TextView) findViewById(R.id.tv_details_voteAverage);

        bindSelectedMovieData();
    }

    private void bindSelectedMovieData() {
        Intent intent = getIntent();
        Movies selectedMovie = (Movies)intent.getParcelableExtra("Movie");

        if (selectedMovie == null) {
            throw new AssertionError();
        }
        String title2=selectedMovie.getTitle();
        String title=selectedMovie.getTitle();
        String language=selectedMovie.getOriginalLanguage();
        String overview=selectedMovie.getOverview();
        String releaseDate=selectedMovie.getReleaseDate();
        Double vote=selectedMovie.getVoteAverage();

        movieTitle.setText(title);
        movieLanguage.setText(new StringBuilder("Language: ").append(language));
        moviePlot.setText(overview);
        movieReleaseDate.setText(new StringBuilder("Release Date: ").append(releaseDate));
        movieVoteAverage.setText(new StringBuilder("Rating: ").append(vote));

        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttp3Downloader(this));
        builder.build().load(this.getResources().getString(R.string.IMAGE_BASE_URL) + selectedMovie.getBackdropPath())
                .placeholder((R.drawable.gradient_background))
                .error(R.drawable.ic_launcher_background)
                .into(moviePoster);
    }
}
