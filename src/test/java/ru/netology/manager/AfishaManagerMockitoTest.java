package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.domain.Movie;
import ru.netology.repository.AfishaRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AfishaManagerMockitoTest {
    @Mock
    private AfishaRepository repository;
    @InjectMocks
    AfishaManager manager = new AfishaManager(repository);

    private Movie first = new Movie(1, "NumberOne", "actionMovie");
    private Movie second = new Movie(2, "NumberTwo", "cartoon");
    private Movie third = new Movie(3, "NumberThree", "comedy");
    private Movie fourth = new Movie(4, "NumberFour", "actionMovie");
    private Movie fifth = new Movie(5, "NumberFour", "horrors");
    private Movie six = new Movie(6, "NumberSix", "cartoon");
    private Movie seventh = new Movie(7, "NumberSeven", "comedy");
    private Movie eighth = new Movie(8, "NumberEight", "actionMovie");
    private Movie ninth = new Movie(9, "NumberNine", "actionMovie");
    private Movie tenth = new Movie(10, "NumberTen", "actionMovie");
    private Movie eleven = new Movie(11, "NumberEleven", "horrors");

    Movie[] expected = {tenth, ninth, eighth, seventh, six, fifth, fourth, third, second, first};

    @BeforeEach
    void setUp() {
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
    }

    @Test
    void mustShowTenMovie() {
        Movie[] returned = new Movie[]{first, second, third, fourth, fifth, six, seventh, eighth, ninth, tenth};
        doReturn(returned).when(repository).findAll();
        Movie[] actual = manager.getAll();
        Movie[] expected = new Movie[]{tenth, ninth, eighth, seventh, six, fifth, fourth, third, second, first};
        assertArrayEquals(expected, actual);
        verify(repository).findAll();
    }

    @Test
    void mustShowMoreThanTen() {
        Movie[] returned = new Movie[]{first, second, third, fourth, fifth, six, seventh, eighth, ninth, tenth, eleven};
        doReturn(returned).when(repository).findAll();
        Movie[] actual = manager.getAll();
        Movie[] expected = new Movie[]{eleven, tenth, ninth, eighth, seventh, six, fifth, fourth, third, second};
        assertArrayEquals(expected, actual);
        verify(repository).findAll();
    }

    @Test
    void mustShowLessThanTen() {
        Movie[] returned = new Movie[]{first, second, third, fourth, fifth, six, seventh, eighth, ninth};
        doReturn(returned).when(repository).findAll();
        Movie[] actual = manager.getAll();
        Movie[] expected = new Movie[]{ninth, eighth, seventh, six, fifth, fourth, third, second, first};
        assertArrayEquals(expected, actual);
        verify(repository).findAll();
    }

    @Test
    void addMovie() {
        Movie[] returned = new Movie[]{first, second, third, fourth, fifth, six, seventh, eighth, ninth, tenth, eleven};
        doReturn(returned).when(repository).findAll();
        doNothing().when(repository).save(eleven);
        manager.addMovie(eleven);
        Movie[] expected = new Movie[]{eleven, tenth, ninth, eighth, seventh, six, fifth, fourth, third, second};
        Movie[] actual = manager.getAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void addMovieValid() {
        Movie[] returned = new Movie[]{fifth};
        doReturn(returned).when(repository).findAll();
        manager.addMovie(fifth);
        Movie[] expected = new Movie[]{fifth};
        Movie[] actual = manager.getAll();
        assertArrayEquals(expected, actual);
        verify(repository).findAll();
    }

    @Test
    void showNothing() {
        Movie[] returned = new Movie[0];
        doReturn(returned).when(repository).findAll();
        Movie[] expected = new Movie[0];
        Movie[] actual = manager.getAll();
        assertArrayEquals(expected, actual);
        verify(repository).findAll();
    }

    @Test
    void showNumberFilms() {
        manager.setCustomMovieLength(5);
        Movie[] returned = new Movie[]{first, second, third};
        doReturn(returned).when(repository).findAll();
        Movie[] actual = manager.getAll();
        Movie[] expected = new Movie[]{third, second, first};
        assertArrayEquals(expected, actual);
        verify(repository).findAll();
    }

    @Test
    void showAll() {
        Movie[] returned = new Movie[]{first, second, third, fourth, fifth, six, seventh, eighth, ninth, tenth};
        doReturn(returned).when(repository).findAll();
        Movie[] expected = new Movie[]{first, second, third, fourth, fifth, six, seventh, eighth, ninth, tenth};
        Movie[] actual = manager.showAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldShowLess() {
        manager.setCustomMovieLength(3);
        Movie[] returned = new Movie[]{first, second, third, fourth, fifth, six, seventh, eighth, ninth, tenth};
        doReturn(returned).when(repository).findAll();
        Movie[] actual = manager.getAll();
        Movie[] expected = new Movie[]{tenth, ninth, eighth};
        assertArrayEquals(expected, actual);
        verify(repository).findAll();
    }

    @Test
    void shouldShowLessCustom() {
        AfishaManager manager = new AfishaManager(repository, 0);
        Movie[] returned = new Movie[]{first, second, third, fourth, fifth};
        doReturn(returned).when(repository).findAll();
        Movie[] actual = manager.getAll();
        Movie[] expected = new Movie[]{fifth, fourth, third, second, first};
        assertArrayEquals(actual, expected);
        verify(repository).findAll();
    }
}