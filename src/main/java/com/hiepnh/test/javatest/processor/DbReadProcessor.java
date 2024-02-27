package com.hiepnh.test.javatest.processor;

import com.hiepnh.test.javatest.entities.AnimalEntity;
import com.hiepnh.test.javatest.model.Animal;
import com.hiepnh.test.javatest.model.Cat;
import com.hiepnh.test.javatest.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Qualifier("dbReadProcessor")
public class DbReadProcessor extends IOReadProcessor{

    private final AnimalRepository animalRepository;

    public DbReadProcessor(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    protected List<Animal> readData() {
        Pageable pageable = PageRequest.of(0, 1000);
        Page<AnimalEntity> animalEntityList = animalRepository.findAll(pageable);
        return animalEntityList.getContent().stream()
                .map(data -> {
                    Animal animal;
                    if (data.getType() == 0) {
                        animal = new Cat();
                    } else {
                        animal = new Cat();
                    }
                    animal.setName(data.getName());
                    animal.setLeg(data.getLegs());
                    animal.setType(data.getType());
                    return animal;
                }).collect(Collectors.toList());
    }
}
