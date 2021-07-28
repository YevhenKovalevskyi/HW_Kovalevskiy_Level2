package ru.gb.homeworks.hw05.task1;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * Special Class represents special methods for working with threads
 *
 * @author e.kovalevskiy
 * @version 1.0
 */
@Slf4j
public class Special {
    
    /**
     * The method solves the task using 2 threads
     *
     * @param array for recalculate
     */
    public static void twoStreamsSpecialMethod(double[] array) {
        Set<String> streams = new HashSet<>();
        
        long timeStart = System.currentTimeMillis();
        
        int length = array.length;
        int middle = length / 2;
        
        double[] arrayFull  = new double[length];
        double[] arrayPart1 = new double[middle];
        double[] arrayPart2 = new double[length - middle];
        
        System.arraycopy(array, 0, arrayPart1, 0, middle);
        System.arraycopy(array, middle, arrayPart2, 0, length - middle);
        
        try {
            Thread t1 = new Thread(() -> handleArray(arrayPart1, streams));
            Thread t2 = new Thread(() -> handleArray(arrayPart2, streams));
            
            t1.start();
            t2.start();
            
            t1.join();
            t2.join();
            
            System.arraycopy(arrayPart1, 0, arrayFull, 0, middle);
            System.arraycopy(arrayPart2, 0, arrayFull, middle, length - middle);
        } catch (InterruptedException e) {
            log.error("The thread has been interrupted. {}", e.getMessage());
            e.printStackTrace();
        }
        
        long timeEnd = System.currentTimeMillis();
        
        System.out.printf("%d millis by streams: %s\n", timeEnd - timeStart, Arrays.toString(streams.toArray()));
    }
    
    /**
     * The method recalculates array items
     *
     * @param array for recalculate
     * @param streams for saving thread name
     */
    private static void handleArray(double[] array, Set<String> streams) {
        IntStream.range(0, array.length)
                .peek(i -> streams.add(Thread.currentThread().getName()))
                .forEach(i -> {
                    array[i] = (float)(array[i] * Math.sin(0.2d + i / 5.) * Math.cos(0.2d + i / 5.) * Math.cos(0.4d + i / 2.));
                });
    }
}
