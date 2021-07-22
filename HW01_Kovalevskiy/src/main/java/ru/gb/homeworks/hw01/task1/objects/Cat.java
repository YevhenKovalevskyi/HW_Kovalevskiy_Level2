package ru.gb.homeworks.hw01.task1.objects;

import lombok.Value;
import ru.gb.homeworks.hw01.task1.contracts.Entity;

@Value
public class Cat implements Entity {
    
    int height;
    int width;
    
    public String run() {
        return "Cat is running";
    }
    
    public String jump() {
        return "Cat is jumping";
    }
    
    public String getEntityName() {
        return "Cat";
    }
}
