package com.apps.washington.moviemammal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.apps.washington.moviemammal.adapter.MovieAdapter;
import com.apps.washington.moviemammal.constructor.Movie;
import com.apps.washington.moviemammal.utils.QueryUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a new ArrayList with the parsed movie data
        ArrayList<Movie> popularMoviesList = QueryUtils.getPopularMovies();

        // Find the ListView with the ID "list"
        final GridView gridView = findViewById(R.id.grid);

        // Create a new adapter that has the popular movies list as input
        final MovieAdapter adapter = new MovieAdapter(this, popularMoviesList);

        // Set the adapter on the ListView
        gridView.setAdapter(adapter);

        /*
         Set an item click listener on the ListView. It sends an intent to a web browser
         to open a website with more information about the selected movie
          */
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                // Find the current movie that was clicked on
                Movie currentMovie = adapter.getItem(position);
                Uri movieUri = Uri.parse("https://www.themoviedb.org/");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, movieUri);
                startActivity(webIntent);
            }
        });
    }
}
