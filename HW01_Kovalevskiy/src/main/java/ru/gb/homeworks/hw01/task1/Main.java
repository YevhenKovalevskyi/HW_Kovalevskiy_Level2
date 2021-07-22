package ru.gb.homeworks.hw01.task1;

import lombok.extern.slf4j.Slf4j;
import ru.gb.homeworks.hw01.task1.contracts.Entity;
import ru.gb.homeworks.hw01.task1.contracts.Obstacle;
import ru.gb.homeworks.hw01.task1.objects.*;

/**
 * Main Class represents homework #1 #task1
 *
 * @author e.kovalevskiy
 * @version 1.0
 */
@Slf4j
public class Main {
    
    public static void main(String[] args) {
        log.info("Start program...");
        
        Entity[] entities = {
                new Human(2, 3),
                new Cat(6, 8),
                new Robot(0, 12),
        };
    
        Obstacle[] obstacles = {
                new Treadmill(4),
                new Wall(2),
        };
        
        try {
            run(entities, obstacles);
        } catch (NullPointerException | IllegalArgumentException e) {
            String message = "An error has been detected. One or both arrays are incorrect!";
            log.error(message);
            System.out.println(message);
        }
    
        log.info("End program.\n");
    }
    
    /**
     * The method operates the behavior of entities
     *
     * @param entities for checking
     * @param obstacles for overcome
     */
    private static void run(Entity[] entities, Obstacle[] obstacles) {
        if (entities == null || obstacles == null) {
            log.error("Array is null! Can't handle null arrays!");
            throw new NullPointerException("Can't handle null arrays!");
        }
    
        if (entities.length == 0 || obstacles.length == 0) {
            log.error("Array is empty! Can't handle empty arrays!");
            throw new IllegalArgumentException("Can't handle empty arrays!");
        }
        
        for (Entity entity: entities) {
            for (Obstacle obstacle: obstacles) {
                if (!obstacle.can(entity)) {
                    System.out.printf("%s: has not enough \"%s\" to continue\n", entity.getEntityName(), obstacle.getObstacleName());
                    break;
                }
    
                System.out.println(obstacle.make(entity));
            }
    
            System.out.println("------");
        }
    
        log.info("All entities were checked.");
    }
}
