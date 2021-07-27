package leraning.reactive.netflux.services;

import java.time.Duration;
import java.util.Date;
import leraning.reactive.netflux.domain.Movie;
import leraning.reactive.netflux.domain.MovieEvent;
import leraning.reactive.netflux.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public Mono<Movie> getMovieById(String id) {
        return movieRepository.findById(id);
    }

    @Override
    public Flux<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Flux<MovieEvent> streamMovieEvents(String id) {
        return Flux
                .<MovieEvent>generate(
                        movieEventSynchronousSink -> movieEventSynchronousSink.next(new MovieEvent(id, new Date()))
                        )
                .delayElements(Duration.ofSeconds(1L));
    }
}
