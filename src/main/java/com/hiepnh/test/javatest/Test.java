package com.hiepnh.test.javatest;

import com.hiepnh.test.javatest.model.Animal;
import com.hiepnh.test.javatest.model.Cat;
import com.hiepnh.test.javatest.model.Duck;
import com.hiepnh.test.javatest.processor.IOReadProcessor;
import com.hiepnh.test.javatest.processor.IOWriteProcessor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

@Component
public class Test implements CommandLineRunner {

    private final IOWriteProcessor fileWriteProcessor;

    private final IOWriteProcessor dbWriteProcessor;

    private final IOReadProcessor fileReadProcessor;

    private final IOReadProcessor dbReadProcessor;

    public Test(@Qualifier("fileWriteProcessor") IOWriteProcessor fileWriteProcessor,
                @Qualifier("dbWriteProcessor") IOWriteProcessor dbWriteProcessor,
                @Qualifier("fileReadProcessor") IOReadProcessor fileReadProcessor,
                @Qualifier("dbReadProcessor") IOReadProcessor dbReadProcessor) {
        this.fileWriteProcessor = fileWriteProcessor;
        this.dbWriteProcessor = dbWriteProcessor;
        this.fileReadProcessor = fileReadProcessor;
        this.dbReadProcessor = dbReadProcessor;
    }

    private final int SIZE = 1000;

    private List<Animal> initData() {
        List<Animal> result  = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < SIZE; i++) {
            int ranValue = random.nextInt();
            Animal animal;
            int type = ranValue % 2;
            if (type == 0) {
                animal = new Cat();
                animal.setName("Hiep Cat " + i);
                animal.setLeg(4);
            } else {
                animal = new Duck();
                animal.setName("Hiep Duck " + i);
                animal.setLeg(2);
            }
            animal.setType(type);
            result.add(animal);
        }
        return result;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Animal> data = initData();

        fileWriteProcessor.setNameJob("IO");
        fileWriteProcessor.setAnimals(data);
        dbWriteProcessor.setNameJob("DB");
        dbWriteProcessor.setAnimals(data);

        Thread writeFileThread = new Thread(fileWriteProcessor);
        Thread writeDbThread = new Thread(dbWriteProcessor);

        writeFileThread.start();
        writeDbThread.start();

        writeFileThread.join();
        writeDbThread.join();

        System.out.println("Write Done");

        List<Animal> fileAnimals = fileReadProcessor.call();
        System.out.println(fileAnimals);
        List<Animal> dbAnimals = dbReadProcessor.call();
        System.out.println(dbAnimals);

        if(fileAnimals.size() != SIZE || dbAnimals.size() != SIZE) {
            System.out.println("FALSE");
            return;
        }

        for (int i = 0; i < SIZE; i++) {
            Animal dbAnimal = dbAnimals.get(i);
            Animal fileAnimal = fileAnimals.get(i);

            if (!dbAnimal.getName().equals(fileAnimal.getName())) {
                System.out.println("FALSE");
                return;
            }
        }
        System.out.println("TRUE");
    }
}
