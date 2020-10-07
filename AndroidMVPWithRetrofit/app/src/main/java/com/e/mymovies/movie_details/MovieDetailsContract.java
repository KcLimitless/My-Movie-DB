/**
 * @file MovieDetailsContract.java
 * @brief This is the contract class for Movie details MVP
 * @author Shrikant
 * @date 15/04/2018
 */
package com.e.mymovies.movie_details;

import com.e.mymovies.model.Movie;

public interface MovieDetailsContract {

    interface Model {

        interface OnFinishedListener {
            void onFinished(Movie movie);

            void onFailure(Throwable t);
        }

        void getMovieDetails(OnFinishedListener onFinishedListener, int movieId);
    }

    interface View {

        void showProgress();

        void hideProgress();

        void setDataToViews(Movie movie);

        void onResponseFailure(Throwable throwable);
    }

    interface Presenter {
        void onDestroy();

        void requestMovieData(int movieId);
    }
}
