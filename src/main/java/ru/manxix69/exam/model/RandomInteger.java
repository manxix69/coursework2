package ru.manxix69.exam.model;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomInteger extends Random {
    public RandomInteger() {
    }

    public int nextInt(int origin, int bound) {
        return super.nextInt(origin, bound);
    }
}
