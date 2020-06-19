package com.advancedjava.springbootmvc.entity;

import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SuperMathsTest {
    @Autowired
    private SuperMaths superMaths;

    @BeforeClass
    public static void initClass() {
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void checkSuperMathsBeanExists() {
        assertThat(superMaths).isNotNull();
    }

    @Test
    void add() {
        assertThat(superMaths.add(1, 2)).isEqualTo(3);
    }

    @Test
    void substract() {
        assertThat(superMaths.substract(1, 2)).isEqualTo(-1);
    }
}
