package com.hiepnh.test.javatest.model;

import lombok.Data;

@Data
public abstract class Animal {

    private Long id;

    private String name;

    private int type;

    private int leg;


    public abstract void spark();
}
