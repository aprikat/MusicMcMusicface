package com.fivestars.april.musicmcmusicface;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SpotifyService {

    @GET("artists/{artistId}")
    Call<Artist> getArtist(@Path("artistId") String id);

}
