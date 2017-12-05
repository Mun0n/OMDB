package com.mun0n.domain.interactor;

import io.reactivex.observers.DisposableObserver;

public class DefaultObserver<T> extends DisposableObserver<T> {
    @Override
    public void onNext(final T value) {
    
    }
    
    @Override
    public void onError(final Throwable e) {
    
    }
    
    @Override
    public void onComplete() {
    
    }
}
