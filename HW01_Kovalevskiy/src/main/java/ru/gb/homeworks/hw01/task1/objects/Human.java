package ru.gb.homeworks.hw01.task1.objects;

import lombok.Value;
import ru.gb.homeworks.hw01.task1.contracts.Entity;

@Value
public class Human implements Entity {
    
    int height;
    int width;
    
    public String run() {
        return "Human is running";
    }
    
    public String jump() {
        return "Human is jumping";
    }
    
    public String getEntityName() {
        return "Human";
    }
}
