/**
 * @file MovieListModel.java
 * @brief This is model class for list screen, it will handle all business logic.
 * @author Shrikant
 * @date 14/04/2018
 */

package com.e.mymovies.movie_list;

import android.util.Log;

import java.util.List;

import com.e.mymovies.model.Movie;
import com.e.mymovies.model.MovieListResponse;
import com.e.mymovies.network.ApiClient;
import com.e.mymovies.network.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.e.mymovies.network.ApiClient.API_KEY;

public class MovieListModel implements MovieListContract.Model {

    private final String TAG = "MovieListModel";


    /**
     * This function will fetch movies data
     * @param onFinishedListener
     * @param pageNo : Which page to load.
     */
    @Override
    public void getMovieList(final OnFinishedListener onFinishedListener, int pageNo) {

        ApiInterface apiService;
        apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<MovieListResponse> call = apiService.getPopularMovies(API_KEY, pageNo);
        call.enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                List<Movie> movies = response.body().getResults();
                Log.d(TAG, "Number of movies received: " + movies.size());
                onFinishedListener.onFinished(movies);
            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t);
            }
        });
    }

}
