package com.hiepnh.test.javatest.processor;

import com.hiepnh.test.javatest.entities.AnimalEntity;
import com.hiepnh.test.javatest.model.Animal;
import com.hiepnh.test.javatest.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Qualifier("dbWriteProcessor")
public class DbWriteProcessor extends IOWriteProcessor {

    private final AnimalRepository animalRepository;

    public DbWriteProcessor(List<Animal> animals, AnimalRepository animalRepository) {
        super(animals);
        this.animalRepository = animalRepository;
    }

    @Override
    protected void writeData() {
        List<AnimalEntity> animalEntities = this.getAnimals().stream()
                .map(data -> {
                    AnimalEntity animalEntity = new AnimalEntity();
                    animalEntity.setName(data.getName());
                    animalEntity.setLegs(data.getLeg());
                    animalEntity.setType(data.getType());
                    return animalEntity;
                }).collect(Collectors.toList());

        animalRepository.saveAll(animalEntities);
    }
}
