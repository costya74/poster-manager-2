package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Movie;

import static org.junit.jupiter.api.Assertions.*;

class MovieManagerTest {
    MovieManager manager = new MovieManager();

    private Movie first = new Movie(1, "NumberOne", "actionMovie");
    private Movie second = new Movie(2, "NumberTwo", "cartoon");
    private Movie third = new Movie(3, "NumberThree", "comedy");
    private Movie fourth = new Movie(4, "NumberFour", "actionMovie");
    private Movie fifth = new Movie(5, "NumberFire",  "horrors");
    private Movie six = new Movie(6, "NumberSix",  "cartoon");
    private Movie seventh = new Movie(7, "NumberSeven", "comedy");
    private Movie eighth = new Movie(8, "NumberEight",  "actionMovie");
    private Movie ninth = new Movie(9, "NumberNine",  "actionMovie");
    private Movie tenth = new Movie(10, "NumberTen",  "actionMovie");


    Movie[] expected = {tenth, ninth, eighth, seventh, six, fifth, fourth, third, second, first};



    @Test
    void mustShowTenMovie() {
        MovieManager manager = new MovieManager(10);
        manager.addMovie(first);
        manager.addMovie(second);
        manager.addMovie(third);
        manager.addMovie(fourth);
        manager.addMovie(fifth);
        manager.addMovie(six);
        manager.addMovie(seventh);
        manager.addMovie(eighth);
        manager.addMovie(ninth);
        manager.addMovie(tenth);
        Movie[] actual = manager.getLastAdd();
        assertArrayEquals(expected, actual);
    }

    @Test
    void mustShowNothing() {
        MovieManager manager = new MovieManager(0);
        Movie[] actual = manager.getLastAdd();
        Movie[] expected = new Movie[0];
        assertArrayEquals(expected, actual);
    }

    @Test
    void mustShowLessMin() {
        MovieManager manager = new MovieManager(-1);
        Movie[] actual = manager.getLastAdd();
        Movie[] expected = new Movie[0];
        assertArrayEquals(expected, actual);
    }

    @Test
    void mustShowValid() {
        MovieManager manager = new MovieManager(5);
        manager.addMovie(first);
        manager.addMovie(second);
        manager.addMovie(third);
        manager.addMovie(fourth);
        manager.addMovie(fifth);
        manager.addMovie(six);
        manager.addMovie(seventh);
        manager.addMovie(eighth);
        manager.addMovie(ninth);
        manager.addMovie(tenth);
        Movie movieToAdd = new Movie(11, "NumberFive", "horrors");
        manager.addMovie(movieToAdd);
        Movie[] actual = manager.getLastAdd();
        Movie[] expected = {new Movie(11, "NumberFive", "horrors"), tenth, ninth, eighth, seventh};
        assertArrayEquals(expected, actual);

    }

    @Test
    void mustShowOverMax() {
        MovieManager manager = new MovieManager(11);
        manager.addMovie(first);
        manager.addMovie(second);
        manager.addMovie(third);
        manager.addMovie(fourth);
        manager.addMovie(fifth);
        manager.addMovie(six);
        manager.addMovie(seventh);
        manager.addMovie(eighth);
        manager.addMovie(ninth);
        manager.addMovie(tenth);

        Movie[] actual = manager.getLastAdd();
        assertArrayEquals(expected, actual);

    }
}