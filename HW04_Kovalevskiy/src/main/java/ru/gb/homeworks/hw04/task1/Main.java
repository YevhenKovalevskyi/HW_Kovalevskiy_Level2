package ru.gb.homeworks.hw04.task1;

import lombok.extern.slf4j.Slf4j;
import ru.gb.homeworks.hw04.task1.components.ApplicationFrame;

/**
 * Main Class represents homework #4 #task1
 *
 * @author e.kovalevskiy
 * @version 1.0
 */
@Slf4j
public class Main {
    
    public static void main(String[] args) {
        log.info("Start program...");
        
        new ApplicationFrame();
    
        log.info("End program.\n");
    }
}
