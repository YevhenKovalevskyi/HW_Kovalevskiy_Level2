package ru.gb.homeworks.hw06.task1.client;

import lombok.extern.slf4j.Slf4j;

/**
 * Main Class represents homework #6 #task1
 *
 * @author e.kovalevskiy
 * @version 1.0
 */
@Slf4j
public class Main {
    
    public static void main(String[] args) {
        log.info("Start program...");
        System.out.println();
        
        new Client();

        log.info("End program.\n");
    }
}
