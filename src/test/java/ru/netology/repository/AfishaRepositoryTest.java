package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Movie;

import static org.junit.jupiter.api.Assertions.*;

class AfishaRepositoryTest {
    private AfishaRepository repository = new AfishaRepository();

    private Movie first = new Movie(1, "NumberOne",  "actionMovie");
    private Movie second = new Movie(2, "NumberTwo",  "cartoon");
    private Movie third = new Movie(3, "NumberThree",  "comedy");
    private Movie fourth = new Movie(4, "NumberFour",  "actionMovie");
    private Movie fifth = new Movie(5, "NumberFive",  "horrors");
    private Movie six = new Movie(6, "NumberSix",  "cartoon");
    private Movie seventh = new Movie(7, "NumberSeven",  "comedy");
    private Movie eighth = new Movie(8, "NumberEight",  "actionMovie");
    private Movie ninth = new Movie(9, "NumberNine",  "actionMovie");
    private Movie tenth = new Movie(10, "NumberTen",  "actionMovie");

    @BeforeEach
    void setUp() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);
        repository.save(fifth);
        repository.save(six);
        repository.save(seventh);
        repository.save(eighth);
        repository.save(ninth);
        repository.save(tenth);

    }


    @Test
    void mustFindAll() {
        repository.findAll();
        Movie[] actual = repository.findAll();
        Movie[] expected = new Movie[]{first, second, third, fourth, fifth, six, seventh, eighth, ninth, tenth,};
        assertArrayEquals(expected, actual);

    }

    @Test
    void mustSave() {
        Movie movieToAdd = new Movie(12, "NumberTwelve",  "horrors");
        repository.save(movieToAdd);
        Movie[] actual = repository.findAll();
        Movie[] expected = {first, second, third, fourth, fifth, six, seventh, eighth, ninth, tenth,
                new Movie(12, "NumberTwelve",  "horrors")

        };

        assertArrayEquals(expected, actual);

    }

    @Test
    void shouldFindById() {
        Movie actual = repository.findById(5);
        Movie expected = new Movie(5, "NumberFour",  "horrors");
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindInvalidId() {
        Movie actual = repository.findById(100);
        Movie expected = null;
        assertEquals(expected, actual);

    }

    @Test
    void shouldRemoveById() {
        repository.removeById(5);
        Movie[] actual = repository.findAll();
        Movie[] expected = new Movie[]{first, second, third, fourth, six, seventh, eighth, ninth, tenth,
        };
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoveAll() {
        repository.removeAll();
        Movie[] actual = repository.findAll();
        Movie[] expected = new Movie[]{};
        assertArrayEquals(expected, actual);
    }


}