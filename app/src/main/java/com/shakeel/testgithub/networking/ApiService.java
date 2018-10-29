package com.shakeel.testgithub.networking;

import com.shakeel.testgithub.models.Follower;
import com.shakeel.testgithub.models.User;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Path;

public interface ApiService {

    // Auth with my generated Access Token
   // @GET("{username}?access_token=fdab4319644qewqeqwa00f7c5ddeebc016f")
     @GET("{username}?access_token=<Your Access Token as above comment>")
    
    Call<User> getUser(@HeaderMap Map<String, String> userHeaders, @Path("username") String username);

   // @GET("{username}/followers?access_token=fdab431e2dfadfafa0f7cadfaf5ddeebc016f")
    @GET("{username}/followers?access_token=<Your Access Token as above comment>")
    Call<List<Follower>> getFollower(@HeaderMap Map<String, String> userHeaders, @Path("username") String username);
}
