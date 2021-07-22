package ru.gb.homeworks.hw02.task1.exceptions;

public class MyArraySizeException extends Exception {
    
    private static final String ERROR_MESSAGE = "Can't handle %s array! Available size is: %s.";
    
    String errorSize;
    String rightSize;
    
    public MyArraySizeException(String errorSize, String rightSize) {
        this.errorSize = errorSize;
        this.rightSize = rightSize;
    }
    
    public String getMessage() {
        return String.format(ERROR_MESSAGE, errorSize, rightSize);
    }
}
