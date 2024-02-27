package com.hiepnh.test.javatest.processor;

import com.hiepnh.test.javatest.model.Animal;

import java.util.List;

public abstract class IOWriteProcessor implements Runnable{

    private List<Animal> animals;

    private String nameJob;

    public IOWriteProcessor(List<Animal> animals) {
        this.animals = animals;
    }

    abstract protected void writeData();

    @Override
    public void run() {
        System.out.println("Start run thread " + this.nameJob);
        this.writeData();
        System.out.println("End run thread " + this.nameJob);

    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public String getNameJob() {
        return nameJob;
    }

    public void setNameJob(String nameJob) {
        this.nameJob = nameJob;
    }
}
