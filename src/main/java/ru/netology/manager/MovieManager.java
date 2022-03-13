package ru.netology.manager;

import lombok.Data;
import ru.netology.domain.Movie;


@Data


public class MovieManager {
    private Movie[] movies = new Movie[0];
    private int defaultMovieLength = 10;


    MovieManager() {

    }

    public MovieManager(int customMovieLength) {
        if (customMovieLength > 0) {
            defaultMovieLength = customMovieLength;
        }

    }

    public void addMovie(Movie movie) {
        int length = movies.length + 1;
        Movie[] tmp = new Movie[length];
        for (int i = 0; i < movies.length; i++) {
            tmp[i] = movies[i];
        }
        int lastMovie = tmp.length - 1;
        tmp[lastMovie] = movie;
        movies = tmp;

    }

    public Movie[] getLastAdd() {
        int moviesLength = movies.length;
        if (moviesLength < defaultMovieLength) {
            defaultMovieLength = moviesLength;
        }
        Movie[] customFilm = new Movie[defaultMovieLength];
        for (int i = 0; i < customFilm.length; i++) {
            int result = moviesLength - i - 1;
            customFilm[i] = movies[result];

        }
        return customFilm;
    }
}
