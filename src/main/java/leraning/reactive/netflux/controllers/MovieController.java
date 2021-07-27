package leraning.reactive.netflux.controllers;

import leraning.reactive.netflux.domain.Movie;
import leraning.reactive.netflux.domain.MovieEvent;
import leraning.reactive.netflux.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    //curl -v http://localhost:8080/movies/id
    @GetMapping("/{id}")
    public Mono<Movie> getMovieById(@PathVariable String id) {
        return movieService.getMovieById(id);
    }

    // curl -v http://localhost:8080/movies
    @GetMapping
    public Flux<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    //curl -v http://localhost:8080/movies/{id}/events
    @GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<MovieEvent> streamMovieEvents(@PathVariable String id) {
        return movieService.streamMovieEvents(id);
    }

}
