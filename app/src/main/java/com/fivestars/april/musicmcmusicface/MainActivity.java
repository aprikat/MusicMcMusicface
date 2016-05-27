package com.fivestars.april.musicmcmusicface;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String BASE_URL = "https://api.spotify.com/v1/";
    private SpotifyService spotifyService;
    private String yeezusId = "5K4W6rqBFWDnAN6FQUkS6x";

    private RecyclerView albumsRecyclerView;
    private AlbumsAdapter albumsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        albumsRecyclerView = (RecyclerView) findViewById(R.id.rv_album_list);
        albumsAdapter = new AlbumsAdapter();
        albumsRecyclerView.setHasFixedSize(true);
        albumsRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        albumsRecyclerView.setAdapter(albumsAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        spotifyService = retrofit.create(SpotifyService.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                doTheThings();
                break;
            default:
                break;
        }
    }

    private void doTheThings() {
        Call<Artist> call = spotifyService.getArtist(yeezusId);
        call.enqueue(new Callback<Artist>() {

            @Override
            public void onResponse(Call<Artist> call, Response<Artist> response) {
                Log.d("artist", Integer.toString(response.code()));
                Artist artist = response.body();
                Log.d("artist", artist.getArtistName());
            }

            @Override
            public void onFailure(Call<Artist> call, Throwable t) {
                Log.d("artist", "spotify call failed");
            }
        });
    }
}
