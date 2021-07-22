package ru.gb.homeworks.hw01.task1.contracts;

public interface Obstacle {
    
    String make(Entity entity);
    boolean can(Entity entity);
    String getObstacleName();
}
