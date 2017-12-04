package com.mun0n.domain.executor;

import io.reactivex.Scheduler;

public interface PostExecutionThread {
    
    Scheduler getScheduler();
}
