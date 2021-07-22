package ru.gb.homeworks.hw03.task1;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collections;

/**
 * Main Class represents homework #3 #task1
 *
 * @author e.kovalevskiy
 * @version 1.0
 */
@Slf4j
public class Main {
    
    public static void main(String[] args) {
        log.info("Start program...");
        
        String[] words = {
                "test", "abc", "qwerty", "abs", "xyz", "abc", "oop", "java", "login", "password", "oop", "qa",
                "abs", "abc", "qwerty", "get", "set", "oop", "main", "loop", "last", "post", "get", "qa",
        };
        
        Arrays.stream(words).distinct().forEach(item -> {
            int totalMatches = Collections.frequency(Arrays.asList(words), item);
            System.out.printf("%s: %d\n", item, totalMatches);
        });
    
        log.info("End program.\n");
    }
}
