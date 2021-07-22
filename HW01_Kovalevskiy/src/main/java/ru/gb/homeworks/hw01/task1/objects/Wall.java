package ru.gb.homeworks.hw01.task1.objects;

import lombok.Value;
import ru.gb.homeworks.hw01.task1.contracts.Entity;
import ru.gb.homeworks.hw01.task1.contracts.Obstacle;

@Value
public class Wall implements Obstacle {
    
    int height;
    
    public boolean can(Entity entity) {
        return entity.getHeight() >= height;
    }
    
    public String make(Entity entity) {
        return entity.jump();
    }
    
    public String getObstacleName() {
        return "height";
    }
}
