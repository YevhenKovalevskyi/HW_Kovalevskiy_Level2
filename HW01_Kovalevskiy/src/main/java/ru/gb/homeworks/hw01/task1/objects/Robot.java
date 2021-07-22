package ru.gb.homeworks.hw01.task1.objects;

import lombok.Value;
import ru.gb.homeworks.hw01.task1.contracts.Entity;

@Value
public class Robot implements Entity {
    
    int height;
    int width;
    
    public String run() {
        return "Robot is running";
    }
    
    public String jump() {
        return "Robot is jumping";
    }
    
    public String getEntityName() {
        return "Robot";
    }
}
