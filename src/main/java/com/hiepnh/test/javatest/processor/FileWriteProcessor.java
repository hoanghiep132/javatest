package com.hiepnh.test.javatest.processor;

import com.hiepnh.test.javatest.model.Animal;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
@Qualifier("fileWriteProcessor")
public class FileWriteProcessor extends IOWriteProcessor {

    public FileWriteProcessor(List<Animal> animals) {
        super(animals);
    }

    @Override
    protected void writeData() {
        String fileDirectory = "src/main/resources/test.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileDirectory, true))){
            for (Animal animal: this.getAnimals()) {
                writer.append(animal.getName())
                        .append(",")
                        .append(String.valueOf(animal.getType()))
                        .append(",")
                        .append(String.valueOf(animal.getLeg()))
                        .append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
