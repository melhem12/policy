package com.policy.service;

import java.util.function.Supplier;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionExecutor {

    @Transactional(propagation = Propagation.REQUIRED)
    public <T> T execute(Supplier<T> action) {
        return action.get();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void execute(Runnable action) {
        action.run();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public <T> T executeInNewTx(Supplier<T> action) {
        return action.get();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void executeInNewTx(Runnable action) {
        action.run();
    }

}