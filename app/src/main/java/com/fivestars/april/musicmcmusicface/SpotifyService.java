package com.fivestars.april.musicmcmusicface;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by April.Luo on 5/26/16.
 */
public interface SpotifyService {

    @GET("artists/{artistId}")
    Call<Artist> getArtist(@Path("artistId") String id);
}
