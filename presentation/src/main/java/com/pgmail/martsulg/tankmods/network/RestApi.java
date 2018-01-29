package com.pgmail.martsulg.tankmods.network;


import com.pgmail.martsulg.tankmods.entity.HotRoot;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by AndroidDeveloper on 28.09.17.
 */

public interface RestApi {

    @GET("api-feed.json")
    Observable<HotRoot> getHot();


}