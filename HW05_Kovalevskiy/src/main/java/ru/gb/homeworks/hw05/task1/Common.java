package ru.gb.homeworks.hw05.task1;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Common Class represents Common methods for working with/without threads
 *
 * @author e.kovalevskiy
 * @version 1.0
 */
@Slf4j
public class Common {
    
    /**
     * The method solves the task using one main thread
     *
     * @param array for recalculate
     */
    public static void oneStreamCommonMethod(double[] array) {
        Set<String> streams = new HashSet<>();
        
        long timeStart = System.currentTimeMillis();
    
        IntStream.range(0, array.length)
                .peek(i -> streams.add(Thread.currentThread().getName()))
                .forEach(i -> {
                    array[i] = (float)(array[i] * Math.sin(0.2d + i / 5.) * Math.cos(0.2d + i / 5.) * Math.cos(0.4d + i / 2.));
                });
        
        long timeEnd = System.currentTimeMillis();
        
        System.out.printf("%d millis by streams: %s\n", timeEnd - timeStart, Arrays.toString(streams.toArray()));
    }
    
    /**
     * The method solves the task using two parallel threads
     *
     * @param array for recalculate
     */
    public static void twoStreamsCommonMethod(double[] array) {
        Set<String> streams = new HashSet<>();
        
        long timeStart = System.currentTimeMillis();
        
        IntStream.range(0, array.length)
                .parallel()
                .peek(i -> streams.add(Thread.currentThread().getName()))
                .forEach(i -> {
                    array[i] = (float)(array[i] * Math.sin(0.2d + i / 5.) * Math.cos(0.2d + i / 5.) * Math.cos(0.4d + i / 2.));
                });
        
        long timeEnd = System.currentTimeMillis();
        
        System.out.printf("%d millis by streams: %s\n", timeEnd - timeStart, Arrays.toString(streams.toArray()));
    }
    
    /**
     * The method solves the task using many parallel threads
     *
     * @param array for recalculate
     */
    public static void manyStreamsCommonMethod(double[] array, int streamCount) {
        Set<String> streams = new HashSet<>();
        AtomicInteger key = new AtomicInteger();
        
        long timeStart = System.currentTimeMillis();
        
        Callable<double[]> task = () -> Arrays.stream(array)
                .parallel()
                .peek(i -> streams.add(Thread.currentThread().getName()))
                .map(i -> {
                    int k = key.incrementAndGet();
                    return (i * Math.sin(0.2d + k / 5.) * Math.cos(0.2d + k / 5.) * Math.cos(0.4d + k / 2.));
                })
                .toArray();
        
        ForkJoinPool pool = new ForkJoinPool(streamCount);
        
        try {
            pool.submit(task).get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("The thread has been interrupted. {}", e.getMessage());
            e.printStackTrace();
        }
        
        long timeEnd = System.currentTimeMillis();
        
        System.out.printf("%d millis by streams: %s\n", timeEnd - timeStart, Arrays.toString(streams.toArray()));
    }
}
