package ru.netology.manager;


import ru.netology.domain.Movie;
import ru.netology.repository.AfishaRepository;


public class AfishaManager {

    private AfishaRepository repository;
    private int defaultMovieLength = 10;
    private int customMovieLength;


    public AfishaManager(AfishaRepository repository, int customMovieLength) {
        this.repository = repository;
        this.customMovieLength = customMovieLength;
    }

    public AfishaManager(AfishaRepository repository) {
        this.repository = repository;
    }


    public void setCustomMovieLength(int customMovieLength) {
        this.customMovieLength = customMovieLength;
    }

    public void addMovie(Movie movie) {
        repository.save(movie);
    }


    public Movie[] getAll() {
        Movie[] movies = repository.findAll();
        int length = movies.length;
        if (customMovieLength <= 0) {
            if (defaultMovieLength < length) {
                length = defaultMovieLength;
            }
        } else {
            if (customMovieLength < length) {
                length = customMovieLength;
            }

        }
        Movie[] result = new Movie[length];
        for (int i = 0; i < result.length; i++) {
            int index = movies.length - i - 1;
            result[i] = movies[index];
        }
        return result;
    }


    public Movie[] showAll() {
        return repository.findAll();
    }


}