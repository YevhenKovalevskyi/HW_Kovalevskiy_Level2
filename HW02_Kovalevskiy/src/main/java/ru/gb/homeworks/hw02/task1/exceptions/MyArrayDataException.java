package ru.gb.homeworks.hw02.task1.exceptions;

public class MyArrayDataException extends Exception {
    
    private static final String ERROR_MESSAGE = "Error data type for %s element! Required int and got String.";
    
    String errorPlace;
    
    public MyArrayDataException(String errorPlace) {
        this.errorPlace = errorPlace;
    }
    
    public String getMessage() {
        return String.format(ERROR_MESSAGE, errorPlace);
    }
}
