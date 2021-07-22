package ru.gb.homeworks.hw02.task1;

import lombok.extern.slf4j.Slf4j;
import ru.gb.homeworks.hw02.task1.exceptions.MyArrayDataException;
import ru.gb.homeworks.hw02.task1.exceptions.MyArraySizeException;
import ru.gb.homeworks.hw02.task1.services.MatrixService;

/**
 * Main Class represents homework #2 #task1
 *
 * @author e.kovalevskiy
 * @version 1.0
 */
@Slf4j
public class Main {
    
    public static void main(String[] args) {
        log.info("Start program...");
        
        String[][] rightMatrixData = {
                {"1", "2", "3", "4"},
                {"4", "3", "2", "1"},
                {"5", "6", "7", "8"},
                {"8", "7", "6", "5"}
        };
    
        String[][] wrongMatrixData1 = {
                {"2", "3", "2", "1"},
                {"12", "0", "1", "test"},
                {"qwerty", "d", "1", "0"},
                {"", "", "", ""}
        };
    
        String[][] wrongMatrixData2 = {
                {"12", "0", "1", "test"},
                {"qwerty", "d", "1", "0"},
                {"", "", "", ""}
        };
    
        handleMatrix(wrongMatrixData1);
        handleMatrix(wrongMatrixData2);
        handleMatrix(rightMatrixData);
    
        log.info("End program.\n");
    }
    
    private static void handleMatrix(String[][] matrix) {
        try {
            if (MatrixService.checkMatrixSize(matrix)) {
                Integer[][] intMatrix = MatrixService.castMatrixElements(matrix);
                int result = MatrixService.sumUpMatrixElements(intMatrix);
    
                log.info("Got matrix elements sum: {}", result);
                System.out.printf("Matrix elements sum is: %d\n", result);
            }
        } catch (IllegalArgumentException | MyArraySizeException | MyArrayDataException e) {
            String errorMessage = e.getMessage();
            log.error("Got exception: {}", errorMessage);
            System.out.println(errorMessage);
        }
    }
}
