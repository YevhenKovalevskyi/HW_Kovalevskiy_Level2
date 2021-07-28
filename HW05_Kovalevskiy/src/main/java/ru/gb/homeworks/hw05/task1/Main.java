package ru.gb.homeworks.hw05.task1;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * Main Class represents homework #5 #task1
 *
 * @author e.kovalevskiy
 * @version 1.0
 */
@Slf4j
public class Main {
    
    public static void main(String[] args) {
        log.info("Start program...");
        System.out.println();
    
        int arrayLength = 10_000_000;
        double[] array = new double[arrayLength];
        Arrays.fill(array, 1);
        
        Common.oneStreamCommonMethod(array);
        Common.twoStreamsCommonMethod(array);
        Common.manyStreamsCommonMethod(array, 8);
    
        Special.twoStreamsSpecialMethod(array);
    
        log.info("End program.\n");
    }
}
