package com.felipe.palma.desafioconcrete.network;

import com.felipe.palma.desafioconcrete.domain.response.RepositoriesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Felipe Palma on 11/07/2019.
 */
public interface IServiceGithubEndPoint {

    @GET("search/repositories")
    Call<RepositoriesResponse> getRepositoryList(
            @Query("q") String q,
            @Query("sort") String sort,
            @Query("page") int page
    );

}
