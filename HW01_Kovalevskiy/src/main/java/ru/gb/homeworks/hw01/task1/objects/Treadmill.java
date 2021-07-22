package ru.gb.homeworks.hw01.task1.objects;

import lombok.Value;
import ru.gb.homeworks.hw01.task1.contracts.Entity;
import ru.gb.homeworks.hw01.task1.contracts.Obstacle;

@Value
public class Treadmill implements Obstacle {

    int width;
    
    public boolean can(Entity entity) {
        return entity.getWidth() >= width;
    }
    
    public String make(Entity entity) {
        return entity.run();
    }
    
    public String getObstacleName() {
        return "width";
    }
}
