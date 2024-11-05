package edu.art.videorental.service;

import edu.art.videorental.model.Movie;
import edu.art.videorental.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(String id) {
        return movieRepository.findById(id);
    }

    public List<Movie> getMoviesByIds(List<String> ids) {
        return movieRepository.findAllById(ids);
    }

    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie updateMovie(String id, Movie movieDetails) {
        return movieRepository.findById(id).map(movie -> {
            movie.setName(movieDetails.getName());
            movie.setPrice(movieDetails.getPrice());
            return movieRepository.save(movie);
        }).orElseThrow(() -> new RuntimeException("Movie not found with id " + id));
    }

    public void deleteMovie(String id) {
        movieRepository.deleteById(id);
    }
}