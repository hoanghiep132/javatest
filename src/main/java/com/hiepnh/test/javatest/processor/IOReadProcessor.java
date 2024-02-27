package com.hiepnh.test.javatest.processor;

import com.hiepnh.test.javatest.model.Animal;

import java.util.List;
import java.util.concurrent.Callable;

public abstract class IOReadProcessor implements Callable<List<Animal>> {

    abstract protected List<Animal> readData();

    @Override
    public List<Animal> call() throws Exception {
        return readData();
    }
}
