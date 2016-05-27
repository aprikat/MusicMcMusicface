package com.fivestars.april.musicmcmusicface;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String BASE_URL = "https://api.spotify.com/v1/";
    private SpotifyService spotifyService;
    private String yeezusId = "5K4W6rqBFWDnAN6FQUkS6x";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
                Log.d("artist", response.body().toString());
                Log.d("artist", response.raw().toString());
                String id = artist.getId();
                String name = artist.getName();
                Log.d("artist", id);
                Log.d("artist", name);
            }

            @Override
            public void onFailure(Call<Artist> call, Throwable t) {
                Log.d("artist", "spotify call failed");
            }
        });

        Call<AlbumsResponse> call2 = spotifyService.getAlbums(yeezusId);
        call2.enqueue(new Callback<AlbumsResponse>() {
            @Override
            public void onResponse(Call<AlbumsResponse> call, Response<AlbumsResponse> response) {
                Log.d("albums", Integer.toString(response.code()));
                AlbumsResponse albumsResponse = response.body();
                Log.d("albums", response.body().toString());
                Log.d("albums", response.raw().toString());
                Log.d("albums", albumsResponse.getHref());

                List<AlbumsResponse.Album> albums = albumsResponse.getItems();
                for (AlbumsResponse.Album a : albums) {
                    Log.d("albums", a.getName());
                }
            }

            @Override
            public void onFailure(Call<AlbumsResponse> call, Throwable t) {
                Log.d("albums", "spotify call failed");
            }
        });
    }
}
