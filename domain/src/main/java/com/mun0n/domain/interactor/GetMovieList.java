package com.mun0n.domain.interactor;

import com.mun0n.domain.Movie;
import com.mun0n.domain.executor.PostExecutionThread;
import com.mun0n.domain.executor.ThreadExecutor;
import com.mun0n.domain.repository.MovieRepository;

import java.util.List;

import io.reactivex.Observable;

public class GetMovieList extends UseCase<List<Movie>, Void> {
    
    private final MovieRepository movieRepository;
    
    public GetMovieList(final MovieRepository movieRepository, final ThreadExecutor threadExecutor,
                        final PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.movieRepository = movieRepository;
    }
    
    @Override
    Observable<List<Movie>> buildUseCaseObservable(final Void aVoid) {
        return this.movieRepository.movies();
    }
}
