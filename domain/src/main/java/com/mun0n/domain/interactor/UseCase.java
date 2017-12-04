package com.mun0n.domain.interactor;

import com.mun0n.domain.executor.PostExecutionThread;
import com.mun0n.domain.executor.ThreadExecutor;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class UseCase<T, Params> {
    
    private ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;
    private CompositeDisposable disposables;
    
    public UseCase(final ThreadExecutor threadExecutor,
                   final PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        disposables = new CompositeDisposable();
    }
    
    abstract Observable<T> buildUseCaseObservable(Params params);
    
    public void execute(DisposableObserver<T> observer, Params params) {
        if (observer != null) {
            final Observable<T> observable = this.buildUseCaseObservable(params).subscribeOn(
                    Schedulers.from(threadExecutor)).observeOn(postExecutionThread.getScheduler());
            addDisposable(observable.subscribeWith(observer));
        }
    }
    
    private void addDisposable(final DisposableObserver<T> observer) {
    }
}
