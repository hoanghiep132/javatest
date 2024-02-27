package com.hiepnh.test.javatest.repository;

import com.hiepnh.test.javatest.entities.AnimalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<AnimalEntity, Long> {
}
