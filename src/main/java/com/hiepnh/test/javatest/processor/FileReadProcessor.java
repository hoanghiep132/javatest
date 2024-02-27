package com.hiepnh.test.javatest.processor;

import com.hiepnh.test.javatest.entities.AnimalEntity;
import com.hiepnh.test.javatest.model.Animal;
import com.hiepnh.test.javatest.model.Cat;
import com.hiepnh.test.javatest.model.Duck;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("fileReadProcessor")
public class FileReadProcessor extends IOReadProcessor{

    @Override
    protected List<Animal> readData() {
        String fileDirectory = "src/main/resources/test.txt";
        List<Animal> result = new ArrayList<>();

        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(new FileInputStream(fileDirectory)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Animal animal;
                if (Integer.parseInt(data[1]) == 0) {
                    animal = new Cat();
                } else {
                    animal = new Duck();
                }
                animal.setName(data[0]);
                animal.setType(Integer.parseInt(data[1]));
                animal.setLeg(Integer.parseInt(data[2]));
                result.add(animal);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static void main(String[] args) {
        String fileDirectory = "src/main/resources/test.txt";
        List<Animal> result = new ArrayList<>();

        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(new FileInputStream(fileDirectory)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Animal animal;
                if (Integer.parseInt(data[1]) == 0) {
                    animal = new Cat();
                } else {
                    animal = new Duck();
                }
                animal.setName(data[0]);
                animal.setType(Integer.parseInt(data[1]));
                animal.setLeg(Integer.parseInt(data[2]));
                result.add(animal);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


