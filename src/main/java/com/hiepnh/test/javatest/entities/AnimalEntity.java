package com.hiepnh.test.javatest.entities;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="tbl_animal")
@Data
public class AnimalEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private Integer type;

    @Column(name = "legs")
    private Integer legs;

}
