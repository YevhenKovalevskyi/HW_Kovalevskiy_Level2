package ru.gb.homeworks.hw02.task1.services;

import lombok.extern.slf4j.Slf4j;
import ru.gb.homeworks.hw02.task1.exceptions.MyArrayDataException;
import ru.gb.homeworks.hw02.task1.exceptions.MyArraySizeException;

import java.util.Arrays;

@Slf4j
public class MatrixService {
    
    private static final int SIZE = 4;
    
    public static boolean checkMatrixSize(String[][] matrix) throws MyArraySizeException {
        int ySize = matrix.length;
        
        if (ySize == 0) {
            log.error("Array is empty! Can't handle empty arrays!");
            throw new IllegalArgumentException("Can't handle empty arrays!");
        }
        
        int xSize = matrix[0].length;
        
        if (ySize != SIZE || xSize != SIZE) {
            String errorSize = String.format("%dx%d", ySize, xSize);
            String rightSize = String.format("%dx%d", SIZE, SIZE);
    
            log.error("Matrix size is incorrect. Got {}, required {}", errorSize, rightSize);
            throw new MyArraySizeException(errorSize, rightSize);
        }
    
        log.info("Matrix size is correct");
        
        return true;
    }
    
    public static Integer[][] castMatrixElements(String[][] matrix) throws MyArrayDataException {
        try {
            return Arrays.stream(matrix)
                    .map(inner -> Arrays.stream(inner)
                            .map(Integer::parseInt)
                            .toArray(Integer[]::new))
                    .toArray(Integer[][]::new);
        } catch (NumberFormatException e) {
            String incorrectElement = e.getMessage().split(": ")[1];
            log.error("Matrix elements cast failed. Incorrect element: {}", incorrectElement);
            throw new MyArrayDataException(incorrectElement);
        }
    }
    
    public static int sumUpMatrixElements(Integer[][] matrix) {
        return Arrays.stream(matrix)
                .mapToInt(inner -> Arrays.stream(inner)
                        .mapToInt(Integer::intValue)
                        .sum())
                .sum();
    }
}
