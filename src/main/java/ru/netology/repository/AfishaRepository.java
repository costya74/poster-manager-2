package ru.netology.repository;

import ru.netology.domain.Movie;

public class AfishaRepository {
    Movie[] movies = new Movie[0];

    public Movie[] findAll() {
        return movies;
    }

    public void save(Movie movie) {
        Movie[] tmpArray = new Movie[movies.length + 1];
        System.arraycopy(movies, 0, tmpArray, 0, movies.length);
        int lastMovie = tmpArray.length - 1;
        tmpArray[lastMovie] = movie;
        movies = tmpArray;
    }

    public Movie findById(int id) {
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    void removeById(int id) {
        int length = movies.length - 1;
        Movie[] tmpArray = new Movie[length];
        int i = 0;
        for (Movie movie : movies) {
            if (movie.getId() != id) {
                tmpArray[i] = movie;
                i++;
            }
        }
        movies = tmpArray;
    }

    void removeAll() {
        movies = new Movie[0];
    }

}