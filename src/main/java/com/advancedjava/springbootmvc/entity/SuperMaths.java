package com.advancedjava.springbootmvc.entity;

import org.springframework.stereotype.Component;

@Component
public class SuperMaths {
    public SuperMaths() {
    }

    public int add(int a, int b) {
        return a + b;
    }

    public int substract(int a, int b) {
        return a - b;
    }
}
