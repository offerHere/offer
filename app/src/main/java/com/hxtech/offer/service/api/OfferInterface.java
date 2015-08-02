package com.hxtech.offer.service.api;

import com.hxtech.offer.service.data.Repo;

import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by niejunhong on 15-8-2.
 */
public interface OfferInterface {

  @GET("/users/{user}/repos")
  Repo.List listRepos(@Path("user") String user);
}
