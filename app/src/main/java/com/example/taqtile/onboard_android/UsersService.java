package com.example.taqtile.onboard_android;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by taqtile on 1/4/17.
 */

public interface UsersService {
    @GET("/api/users")
    Call<UsersWrapper> listUsers(@Query("page") int page);
}
